package dev.aggregate.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.common.util.AndroidLogcatLogger
import dev.aggregate.common.util.Logger

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Provides
    fun provideLogger(): Logger = AndroidLogcatLogger()
}
