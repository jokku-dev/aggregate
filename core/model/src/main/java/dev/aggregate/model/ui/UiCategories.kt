package dev.aggregate.model.ui

data class UiCategories(
    val categories: List<UiCategory> = emptyList(),
    val selectedCategory: UiCategory? = null
)
