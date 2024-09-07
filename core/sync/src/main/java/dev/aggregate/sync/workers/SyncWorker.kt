package dev.aggregate.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import dev.aggregate.common.network.di.NewsDispatchers
import dev.aggregate.data.sync.SyncConstraints
import dev.aggregate.sync.Synchronizer.sync
import dev.aggregate.sync.initializers.syncForegroundInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.collections.all

// Like our ViewModel, this worker has repository in its parameters.
// It uses it for performing synchronization

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParameters: androidx.work.WorkerParameters,
    private val preferencesRepository: dev.aggregate.app.data.repository.PreferencesRepository,
    private val newsRepository: dev.aggregate.app.data.repository.NewsRepository,
    @Dispatcher(dev.aggregate.app.common.network.di.NewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : androidx.work.CoroutineWorker(appContext, workerParameters),
    dev.aggregate.app.data.sync.Synchronizer {

    // Setting foreground notification in case of expedited work request
    override suspend fun getForegroundInfo(): androidx.work.ForegroundInfo =
        appContext.syncForegroundInfo()

    override suspend fun doWork(): androidx.work.ListenableWorker.Result =
        withContext(ioDispatcher) {
            androidx.tracing.traceAsync("Sync", 0) {
                val syncedSuccessfully = awaitAll(
                    async { newsRepository.sync() }
                ).all { it }

                if (syncedSuccessfully) {
                    ListenableWorker.Result.success()
                } else {
                    ListenableWorker.Result.retry()
                }
            }
        }

    override suspend fun getChangeListVersions(): ChangeListVersions =
        preferencesRepository.getChangeListVersions()

    override suspend fun updateChangeListVersions(update: ChangeListVersions.() -> ChangeListVersions) {
        TODO("Not yet implemented")
    }

    companion object {
        fun startUpSyncWork() = androidx.work.OneTimeWorkRequestBuilder<DelegatingWorker>()
            // Mark request as important.
            // Determine how a work request behaves in non-expedited state - resource, battery restrictions.
            // In this case we tell to run the work request as regular after restrictions removed
            .setExpedited(androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            // Conditions of request start up, any network type in this case
            .setConstraints(dev.aggregate.app.data.sync.SyncConstraints)
            // Input data is a lightweight container for key/value pair.
            // In this case we use delegation to pass this worker name to factory
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}
