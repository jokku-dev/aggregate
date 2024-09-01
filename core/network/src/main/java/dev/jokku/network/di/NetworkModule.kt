package dev.jokku.network.di

import com.google.samples.apps.nowinandroid.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jokku.network.NewsApi
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun newsApi(okHttpClient: OkHttpClient?): NewsApi {
        return NewsApi(
            baseUrl = BuildConfig.API_BASE_URL,
            apiKey = BuildConfig.API_KEY,
            okHttpClient = okHttpClient,
        )
    }
}