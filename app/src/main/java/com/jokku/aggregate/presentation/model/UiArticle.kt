package com.jokku.aggregate.presentation.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
data class UiArticle(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    @Contextual
    val source: UiArticleSource = UiArticleSource(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val bookmarked: Boolean = false
)