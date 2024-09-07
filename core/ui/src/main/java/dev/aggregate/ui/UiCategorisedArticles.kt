package dev.aggregate.ui

data class UiCategorisedArticles(
    val topCategoryArticles: List<UiTopCategoryArticles> = emptyList(),
    val localTopHeadlines: List<UiArticle> = emptyList()
) {
    fun favoritesAreNotEmpty() = topCategoryArticles.isNotEmpty()
}

data class UiTopCategoryArticles(
    val category: UiText,
    val articles: List<UiSubcategoryArticles>
)

data class UiSubcategoryArticles(
    val category: UiText,
    val articles: List<UiArticle>
)
