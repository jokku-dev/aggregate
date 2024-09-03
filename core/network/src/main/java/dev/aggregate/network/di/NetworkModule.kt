package dev.aggregate.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aggregate.network.BuildConfig
import dev.aggregate.network.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient? {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }
        return null
    }

    @Singleton
    @Provides
    fun provideNewsApi(okHttpClient: OkHttpClient?): NewsApi {
        return NewsApi(
            baseUrl = BuildConfig.API_BASE_URL,
            apiKey = BuildConfig.API_KEY,
            okHttpClient = okHttpClient,
        )
    }
}