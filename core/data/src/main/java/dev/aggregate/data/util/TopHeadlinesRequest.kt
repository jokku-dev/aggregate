package dev.aggregate.data.util

import dev.aggregate.network.model.Category
import dev.aggregate.network.model.Country

sealed interface TopHeadlinesRequest {
    val query: String?
    val pageSize: Int
    val page: Int
    val bookmarkedArticleIds: Set<String>?
}

data class ByCountryAndCategoryRequest(
    val country: Country? = null,
    val category: Category? = null,
    override val query: String? = null,
    override val pageSize: Int = 100,
    override val page: Int = 1,
    override val bookmarkedArticleIds: Set<String>? = null,
) : TopHeadlinesRequest

data class BySourceRequest(
    val sources: List<String> = emptyList(),
    override val query: String? = null,
    override val pageSize: Int = 100,
    override val page: Int = 1,
    override val bookmarkedArticleIds: Set<String>? = null,
) : TopHeadlinesRequest
