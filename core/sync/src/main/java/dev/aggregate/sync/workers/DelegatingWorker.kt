package dev.aggregate.sync.workers

import android.content.Context
import dagger.hilt.components.SingletonComponent
import kotlin.reflect.KClass

@dagger.hilt.EntryPoint
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface HiltWorkerFactoryEntryPoint {
    fun hiltWorkerFactory(): androidx.hilt.work.HiltWorkerFactory
}

private const val WORKER_CLASS_NAME = "RouterWorkerDelegateClassName"

internal fun KClass<out androidx.work.CoroutineWorker>.delegatedData() =
    androidx.work.Data.Builder()
        .putString(WORKER_CLASS_NAME, qualifiedName)
        .build()

class DelegatingWorker(
    appContext: Context,
    workerParams: androidx.work.WorkerParameters,
) : androidx.work.CoroutineWorker(appContext, workerParams) {
    // get worker name from key/value worker params input data
    private val workerClassName =
        workerParams.inputData.getString(WORKER_CLASS_NAME) ?: ""
    private val delegateWorker =
        // Call a Hilt WorkerFactory from SingletonComponent and ask it to create our worker
        dagger.hilt.android.EntryPointAccessors.fromApplication<HiltWorkerFactoryEntryPoint>(appContext)
            .hiltWorkerFactory()
            .createWorker(appContext, workerClassName, workerParams) as? androidx.work.CoroutineWorker
            ?: throw IllegalArgumentException("Unable to find appropriate worker")

    override suspend fun getForegroundInfo(): androidx.work.ForegroundInfo =
        delegateWorker.getForegroundInfo()

    override suspend fun doWork(): androidx.work.ListenableWorker.Result =
        delegateWorker.doWork()
}