package dev.aggregate.data.sync

import android.util.Log
import dev.aggregate.ui.old.TopHeadlinesRequest
import kotlin.coroutines.cancellation.CancellationException

/**
 * Interface marker for a class that manages synchronization between local data and a remote
 * source for a [Syncable].
 */
interface Synchronizer {
    suspend fun getChangeListVersions(): ChangeListVersions

    suspend fun updateChangeListVersions(update: ChangeListVersions.() -> ChangeListVersions)

    // Convenience function to call repository syncing with auto passing
    // Synchronizer implementing SyncWorker class in our case
    suspend fun Syncable.sync() = this.syncWith(this@Synchronizer)
}

/**
 * Interface marker for a class that is synchronized with a remote source.
 * Syncing must not be performed concurrently and it is the [Synchronizer]'s responsibility to ensure
 * this.
 */
interface Syncable {
    /**
     * Synchronizes the local database backing the repository with the network.
     *
     * Returns if the sync was successful or not
     */
    suspend fun syncWith(synchronizer: Synchronizer): Boolean
}

/**
 * Utility function for syncing a repository with the network.
 *
 * [versionReader] Reads the current version of the model that needs to be synced
 *
 * [changeListFetcher] Fetches the change list for the model
 *
 * [versionUpdater] Updates the [ChangeListVersions] after a successful sync
 *
 * [modelDeleter] Deletes models by consuming the ids of the models that have been deleted
 *
 * [modelUpdater] Updates models by consuming the ids of the models that have changed
 *
 * Note that the blocks defined above are never run concurrently, and the [Synchronizer]
 * implementation must guarantee this.
 */
suspend fun Synchronizer.changeListSync(
    changeListFetcher: suspend (TopHeadlinesRequest) -> List<NetworkChangeList>,
    versionUpdater: ChangeListVersions.(Int) -> ChangeListVersions,
    modelDeleter: suspend (List<String>) -> Unit,
    modelUpdater: suspend (List<String>) -> Unit,
) = suspendRunCatching {
    // Make a GET network request using current version of data
    val changeList = changeListFetcher()
    if (changeList.isEmpty()) return@suspendRunCatching true

    // Paired response list of deleted and new or updated
    val (deleted, updated) = changeList.partition(NetworkChangeList::isDelete)

    // Delete models from database that have been deleted server-side using paired list of ID's for deletion
    modelDeleter(deleted.map(NetworkChangeList::id))

    // Using the change list, pull down and save the changes (akin to a git pull)
    modelUpdater(updated.map(NetworkChangeList::id))

    // Update the last synced version (akin to updating local git HEAD)
    val latestVersion = changeList.last().changeListVersion
    updateChangeListVersions {
        versionUpdater(latestVersion)
    }
}.isSuccess

/**
 * Attempts [block], returning a successful [Result] if it succeeds, otherwise a [Result.Failure]
 * taking care not to break structured concurrency
 */
private suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Log.i(
        "suspendRunCatching",
        "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result",
        exception
    )
    Result.failure(exception)
}