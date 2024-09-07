package dev.aggregate.network

import androidx.annotation.IntRange
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.aggregate.network.model.Category
import dev.aggregate.network.model.Country
import dev.aggregate.network.model.Language
import dev.aggregate.network.model.NetworkArticle
import dev.aggregate.network.model.NetworkArticlesResponse
import dev.aggregate.network.model.NetworkSource
import dev.aggregate.network.model.NetworkSourcesResponse
import dev.aggregate.network.model.SearchIn
import dev.aggregate.network.model.SortBy
import dev.aggregate.network.util.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

/**
 * @see <a href=https://newsapi.org/docs/get-started>
 *     API documentation https://newsapi.org/docs/get-started
 *     </a>
 */
interface NewsApi {
    /**
     * @see <a href="https://newsapi.org/docs/endpoints/everything">
     *     API Everything details https://newsapi.org/docs/endpoints/everything
     *     </a>
     */
    @Suppress("LongParameterList")
    @GET("everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("searchIn") searchIn: List<SearchIn>? = null,
        @Query("sources") sources: List<String>? = null,
        @Query("domains") domains: List<String>? = null,
        @Query("excludeDomains") excludeDomains: List<String>? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: List<Language>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) int: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        // Using Result adapter allows us to avoid crashes, so no need in try catch blocks
    ): Result<NetworkArticlesResponse<NetworkArticle>>

    /**
     * @see <a href="https://newsapi.org/docs/endpoints/everything">
     *     API Top headlines details https://newsapi.org/docs/endpoints/everything
     *     </a>
     */
    @GET("top-headlines")
    suspend fun topHeadlinesByCountryAndCategory(
        @Query("country") country: Country? = null,
        @Query("category") category: Category? = null,
        @Query("q") query: String? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<NetworkArticlesResponse<NetworkArticle>>

    /**
     * @see <a href="https://newsapi.org/docs/endpoints/everything">
     *     API Top headlines details https://newsapi.org/docs/endpoints/everything
     *     </a>
     */
    @GET("top-headlines")
    suspend fun topHeadlinesBySource(
        @Query("sources") sources: List<String>? = null,
        @Query("q") query: String? = null,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<NetworkArticlesResponse<NetworkArticle>>

    /**
     * @see <a href="https://newsapi.org/docs/endpoints/everything">
     *     API Sources details https://newsapi.org/docs/endpoints/everything
     *     </a>
     */
    @GET("top-headlines/sources")
    suspend fun sources(
        @Query("category") category: String? = null,
        @Query("language") language: List<Language>? = null,
        @Query("country") country: String? = null,
    ): Result<NetworkSourcesResponse<NetworkSource>>
}

// Constructor default impl only for public fun
fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi = retrofit(baseUrl, apiKey, okHttpClient, json).create()

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient: OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    return Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}
