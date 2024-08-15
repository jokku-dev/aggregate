package dev.jokku.aggregate.data.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.tracing.traceAsync
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import dev.jokku.aggregate.data.repo.NewsRepository
import dev.jokku.aggregate.data.repo.PreferencesRepository
import dev.jokku.aggregate.data.sync.SyncConstraints
import dev.jokku.aggregate.data.sync.Synchronizer
import dev.jokku.aggregate.data.sync.syncForegroundInfo
import dev.jokku.aggregate.di.Dispatcher
import dev.jokku.aggregate.di.NewsDispatchers.IO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

// Like our ViewModel, this worker has repository in its parameters.
// It uses it for performing synchronization

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val preferencesRepository: PreferencesRepository,
    private val newsRepository: NewsRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParameters), Synchronizer {

    //Setting foreground notification in case of expedited work request
    override suspend fun getForegroundInfo(): ForegroundInfo =
        appContext.syncForegroundInfo()

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        traceAsync("Sync", 0) {
            val syncedSuccessfully = awaitAll(
                async { newsRepository.sync() }
            ).all { it }

            if (syncedSuccessfully) Result.success()
            else Result.retry()
        }
    }

    override suspend fun getChangeListVersions(): ChangeListVersions =
        preferencesRepository.getChangeListVersions()

    override suspend fun updateChangeListVersions(
        update: ChangeListVersions.() -> ChangeListVersions
    ) = preferencesRepository.updateChangeListVersion(update)

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            // Mark request as important. Determine how a work request behaves in non-expedited state - resource, battery restrictions.
            // In this case we tell to run the work request as regular after restrictions removed
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            // Conditions of request start up, any network type in this case
            .setConstraints(SyncConstraints)
            // Input data is a lightweight container for key/value pair. In this case we use
            // delegation to pass this worker name to factory.
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}