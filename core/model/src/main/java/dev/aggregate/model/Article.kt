package dev.aggregate.model

import java.util.Date

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val id: Long = ID_NONE,
    val publishedAt: Date?,
    val source: ArticleSource?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
) {
    companion object {
        const val ID_NONE: Long = 0L
    }
}

data class ArticleSource(
    val id: String,
    val name: String,
)
