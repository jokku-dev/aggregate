package dev.aggregate.model.ui

import dev.aggregate.model.ArticlesResponse
import dev.aggregate.model.UserData
import java.util.Date

data class UiArticlesResponse(
    val articles: List<UiArticle>,
    val responseDate: Date,
) {
    constructor(
        articlesResponse: ArticlesResponse,
        userData: UserData,
    ) : this(
        articles = articlesResponse.articles.map { UiArticle(it, userData) },
        responseDate = articlesResponse.responseDate
    )
}

fun ArticlesResponse?.toUiArticlesResponse(userData: UserData) = UiArticlesResponse(this, userData)
