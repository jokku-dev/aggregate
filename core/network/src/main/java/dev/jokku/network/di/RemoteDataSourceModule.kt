package dev.jokku.network.di


import dev.jokku.network.NewsApi
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    fun newsApi(okHttpClient: OkHttpClient?): NewsApi {
        return NewsApi(
            baseUrl = BuildConfig.API_BASE_URL,
            apiKey = BuildConfig.API_KEY,
            okHttpClient = okHttpClient,
        )
    }
}