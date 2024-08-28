package dev.jokku.ui.old

import dev.jokku.network.model.Category
import dev.jokku.network.model.Country

sealed interface TopHeadlinesRequest {
    val query: String
    val pageSize: Int
    val page: Int
}

data class ByCountryAndCategoryRequest(
    val country: Country,
    val category: Category,
    override val query: String = "",
    override val pageSize: Int = 100,
    override val page: Int = 1
) : TopHeadlinesRequest

data class BySourceRequest(
    val sources: List<String>,
    override val query: String = "",
    override val pageSize: Int = 100,
    override val page: Int = 1
) : TopHeadlinesRequest