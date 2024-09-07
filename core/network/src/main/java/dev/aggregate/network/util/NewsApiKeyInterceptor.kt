package dev.aggregate.network.util

import okhttp3.Interceptor
import okhttp3.Response

// In this case we use interceptor to add api key in request header
internal class NewsApiKeyInterceptor(private val apikey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-Api-Key", apikey)
                .build()
        )
    }
}
