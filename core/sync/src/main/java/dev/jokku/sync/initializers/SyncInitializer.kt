package dev.jokku.sync.initializers

import android.content.Context
import kotlin.apply
import kotlin.jvm.java

object Sync {
    // This method is a workaround to manually initialize the sync process instead of relying on
    // automatic initialization with Androidx Startup. It is called from the app module's
    // Application.onCreate() and should be only done once.
    fun initialize(context: Context) {
        androidx.startup.AppInitializer.getInstance(context)
            .initializeComponent(SyncInitializer::class.java)
    }
}

// This name should not be changed otherwise the app may have concurrent sync requests running
const val SyncFavoriteTopHeadlines = "SyncFavoriteTopHeadlines"

/**
 * Registers work to sync the data layer periodically on app startup.
 */
class SyncInitializer : androidx.startup.Initializer<Sync> {
    override fun create(context: Context): Sync {
        androidx.work.WorkManager.getInstance(context).apply {
            // Run sync on app startup and ensure only one sync worker runs at any time
            enqueueUniqueWork(
                SyncFavoriteTopHeadlines,
                androidx.work.ExistingWorkPolicy.KEEP,
                dev.jokku.sync.workers.SyncWorker.startUpSyncWork()
            )
        }

        return Sync
    }

    override fun dependencies(): List<Class<out androidx.startup.Initializer<*>>> =
        listOf(androidx.work.WorkManagerInitializer::class.java)
}