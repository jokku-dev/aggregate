package dev.aggregate.sync.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.sync.SyncStatusMonitor
import dev.aggregate.sync.WorkManagerSyncStatusMonitor

@Module
@InstallIn(SingletonComponent::class)
interface SyncModule {
    @dagger.Binds
    fun bindsSyncStatusMonitor(
        syncStatusMonitor: WorkManagerSyncStatusMonitor
    ): SyncStatusMonitor
}
