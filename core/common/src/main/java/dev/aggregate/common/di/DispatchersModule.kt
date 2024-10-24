package dev.aggregate.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(NewsDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(NewsDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(NewsDispatchers.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(NewsDispatchers.UNCONFINED)
    fun providesUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val newsDispatcher: NewsDispatchers)

enum class NewsDispatchers {
    DEFAULT,
    IO,
    MAIN,
    UNCONFINED
}
