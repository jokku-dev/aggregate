package dev.jokku.sync

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlin.collections.any

/**
 * Reports on if synchronization is in progress
 */
interface SyncStatusMonitor {
    val isSyncing: Flow<Boolean>
}

/**
 * [SyncStatusMonitor] backed by [WorkInfo] from [WorkManager]
 */
class WorkManagerSyncStatusMonitor @Inject constructor(
    @ApplicationContext context: Context
) : SyncStatusMonitor {
    override val isSyncing: Flow<Boolean> =
        WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(dev.jokku.sync.initializers.SyncFavoriteTopHeadlines)
            .map { it.anyRunning }
            .asFlow()
            // let to get the latest emitted value dropping intermediary
            .conflate()
}

private val List<WorkInfo>.anyRunning get() = any { it.state == WorkInfo.State.RUNNING }