package com.jokku.aggregate.di

import com.jokku.aggregate.data.sync.SyncStatusMonitor
import com.jokku.aggregate.data.sync.WorkManagerSyncStatusMonitor
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