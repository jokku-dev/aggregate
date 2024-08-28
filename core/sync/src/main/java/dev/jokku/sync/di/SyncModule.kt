package dev.jokku.sync.di

import dagger.hilt.components.SingletonComponent

@dagger.Module
@dagger.hilt.InstallIn(dagger.hilt.components.SingletonComponent::class)
interface SyncModule {
    @dagger.Binds
    fun bindsSyncStatusMonitor(
        syncStatusMonitor: dev.jokku.data.sync.WorkManagerSyncStatusMonitor
    ): dev.jokku.data.sync.SyncStatusMonitor
}