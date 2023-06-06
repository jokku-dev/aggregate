package com.jokku.aggregate.di

import android.util.Log
import com.jokku.aggregate.data.remote.HttpRouts.API_KEY_VALUE
import com.jokku.aggregate.data.remote.HttpRouts.API_KEY
import com.jokku.aggregate.data.remote.NewsRemoteDataSource
import com.jokku.aggregate.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.parametersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

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
}