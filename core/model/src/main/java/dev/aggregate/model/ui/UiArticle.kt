package dev.aggregate.model.ui

import dev.aggregate.model.Article

data class UiArticle(
    val author: String = "",
    val bookmarked: Boolean = false,
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: UiArticleSource = UiArticleSource(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val viewed: Boolean = false,
) {
    constructor(
        article: Article,
        bookmarkedArticleIds: Set<String>,
        viewedArticleIds: Set<String>
    ) : this(
        author = article.author,
        bookmarked = article.id in bookmarkedArticleIds,
        content = article.content,
        description = article.description,
        publishedAt = article.publishedAt,
        source = UiArticleSource(article.source.id, article.source.name),
        title = article.title,
        url = article.url,
        urlToImage = article.urlToImage,
        viewed = article.id in viewedArticleIds,
    )
}

data class UiArticleSource(
    val id: String = "",
    val name: String = "",
)

fun List<Article>.toUiArticles(
    bookmarkedArticleIds: Set<String>,
    viewedArticleIds: Set<String>
): List<UiArticle> = map { article ->
    UiArticle(
        article = article,
        bookmarkedArticleIds = bookmarkedArticleIds,
        viewedArticleIds = viewedArticleIds
    )
}
