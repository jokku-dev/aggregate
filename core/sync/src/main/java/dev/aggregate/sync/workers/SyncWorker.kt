package dev.aggregate.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.aggregate.common.network.di.Dispatcher
import dev.aggregate.common.network.di.NewsDispatchers
import dev.aggregate.data.repository.NewsRepository
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.sync.Synchronizer
import dev.aggregate.sync.initializers.SyncConstraints
import dev.aggregate.sync.initializers.syncForegroundInfo
import kotlinx.coroutines.CoroutineDispatcher

// Like our ViewModel, this worker has repository in its parameters.
// It uses it for performing synchronization

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val preferencesRepository: PreferencesRepository,
    private val newsRepository: NewsRepository,
    @Dispatcher(NewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParameters),
    Synchronizer {

    // Setting foreground notification in case of expedited work request
    override suspend fun getForegroundInfo(): ForegroundInfo =
        appContext.syncForegroundInfo()

    override suspend fun doWork(): Result = TODO("Not yet implemented")
//        withContext(ioDispatcher) {
//            traceAsync("Sync", 0) {
//                val syncedSuccessfully = awaitAll(
//                    async { newsRepository.sync() }
//                ).all { it }
//
//                if (syncedSuccessfully) {
//                    Result.success()
//                } else {
//                    Result.retry()
//                }
//            }
//        }

//    override suspend fun getChangeListVersions(): ChangeListVersions =
//        preferencesRepository.getChangeListVersions()

//    override suspend fun updateChangeListVersions(update: ChangeListVersions.() -> ChangeListVersions) {
//    }

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            // Mark request as important.
            // Determine how a work request behaves in non-expedited state - resource, battery restrictions.
            // In this case we tell to run the work request as regular after restrictions removed
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            // Conditions of request start up, any network type in this case
            .setConstraints(SyncConstraints)
            // Input data is a lightweight container for key/value pair.
            // In this case we use delegation to pass this worker name to factory
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}
