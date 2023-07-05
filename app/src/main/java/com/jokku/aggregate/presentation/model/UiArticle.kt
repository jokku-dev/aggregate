package com.jokku.aggregate.presentation.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.String

data class UiArticle(
    val id: Int = 0,
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: UiArticleSource = UiArticleSource(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val bookmarked: Boolean = false
)