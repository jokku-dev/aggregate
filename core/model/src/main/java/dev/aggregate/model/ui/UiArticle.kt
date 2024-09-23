package dev.aggregate.model.ui

import dev.aggregate.model.Article
import dev.aggregate.model.UserData

data class UiArticle(
    val author: String,
    val bookmarked: Boolean,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: UiArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String,
    val viewed: Boolean,
) {
    constructor(
        article: Article,
        userData: UserData
    ) : this(
        author = article.author,
        bookmarked = article.id in userData.bookmarkedArticleIds,
        content = article.content,
        description = article.description,
        publishedAt = article.publishedAt,
        source = UiArticleSource(article.source.id, article.source.name),
        title = article.title,
        url = article.url,
        urlToImage = article.urlToImage,
        viewed = article.id in userData.viewedArticleIds,
    )
}

data class UiArticleSource(
    val id: String,
    val name: String,
)

fun List<Article>?.toUiArticles(userData: UserData): List<UiArticle> = map { UiArticle(it, userData) }
