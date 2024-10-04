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
        author = article.author.toString(),
        bookmarked = article.id.toString() in bookmarkedArticleIds,
        content = article.content.toString(),
        description = article.description.toString(),
        publishedAt = article.publishedAt.toString(),
        source = UiArticleSource(article.source?.id.toString(), article.source?.name.toString()),
        title = article.title.toString(),
        url = article.url.toString(),
        urlToImage = article.urlToImage.toString(),
        viewed = article.id.toString() in viewedArticleIds,
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
