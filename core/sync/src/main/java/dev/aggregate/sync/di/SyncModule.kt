package dev.aggregate.sync.di

import dagger.hilt.components.SingletonComponent

@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface SyncModule {
    @dagger.Binds
    fun bindsSyncStatusMonitor(
        syncStatusMonitor: dev.aggregate.data.sync.WorkManagerSyncStatusMonitor
    ): dev.aggregate.data.sync.SyncStatusMonitor
}
