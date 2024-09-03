package dev.aggregate.ui.old

import dev.aggregate.network.model.Category
import dev.aggregate.network.model.Country

sealed interface TopHeadlinesRequest {
    val query: String
    val pageSize: Int
    val page: Int
}

data class ByCountryAndCategoryRequest(
    val country: Country = Country.AE,
    val category: Category = Category.ENTERTAINMENT,
    override val query: String = "",
    override val pageSize: Int = 100,
    override val page: Int = 1
) : TopHeadlinesRequest

data class BySourceRequest(
    val sources: List<String> = emptyList<String>(),
    override val query: String = "",
    override val pageSize: Int = 100,
    override val page: Int = 1
) : TopHeadlinesRequest