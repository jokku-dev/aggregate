package dev.jokku.newsapi

import androidx.annotation.IntRange
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.jokku.newsapi.model.NetArticle
import dev.jokku.newsapi.model.Language
import dev.jokku.newsapi.model.NetResponse
import dev.jokku.newsapi.model.SortBy
import dev.jokku.newsapi.util.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

/**
 * @see <a href=https://newsapi.org/docs/get-started>API documentation https://newsapi.org/docs/get-started</a>
 */

interface NewsApi {

    /**
     * @see <a href="https://newsapi.org/docs/endpoints/everything">API details https://newsapi.org/docs/endpoints/everything</a>
     */

    @GET("/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: List<Language>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) int: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        // Using Result adapter allows us to avoid crashes, so no need in try catch blocks
    ): Result<NetResponse<NetArticle>>
}

// Constructor default impl only for public fun
fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json,
) : Retrofit {
    val jsonConverterFactory = json.asConverterFactory(MediaType.get("application/json"))

    val modifiedOkHttpClient: OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}