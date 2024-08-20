package dev.jokku.aggregate.di

import dev.jokku.newsdata.sync.SyncStatusMonitor
import dev.jokku.newsdata.sync.WorkManagerSyncStatusMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SyncModule {
    @Binds
    fun bindsSyncStatusMonitor(
        syncStatusMonitor: WorkManagerSyncStatusMonitor
    ): SyncStatusMonitor
}