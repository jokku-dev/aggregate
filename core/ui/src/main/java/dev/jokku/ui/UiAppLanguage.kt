package dev.jokku.ui

data class UiAppLanguage(
    val language: dev.jokku.aggregate.presentation.model.UiText,
    val selected: Boolean = false
)