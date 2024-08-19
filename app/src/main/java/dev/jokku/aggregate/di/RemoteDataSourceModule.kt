package dev.jokku.aggregate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jokku.aggregate.BuildConfig
import dev.jokku.newsapi.NewsApi
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

/*
@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    private const val TIME_OUT = 10_000
    private const val TAG_KTOR_LOGGER = "ktor_logger:"
    private const val TAG_HTTP_STATUS_LOGGER = "http_status:"

    @Singleton
    @Provides
    fun provideRemoteDataSource() : RemoteDataSource = NewsRemoteDataSource(
        client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v(TAG_KTOR_LOGGER, message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                parametersOf(API_KEY, API_KEY_VALUE)
            }
        }
    )
}*/
