package dev.aggregate.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val id: String,
    val publishedAt: String,
    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String,
)

data class ArticleSource(
    val id: String,
    val name: String,
)
