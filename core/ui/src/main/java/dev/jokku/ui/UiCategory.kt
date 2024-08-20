package dev.jokku.ui

import kotlinx.serialization.Contextual

@Serializable
data class UiCategory(
    @Contextual
    val name: dev.jokku.aggregate.presentation.model.UiText,
    @Contextual
    val code: dev.jokku.newsdata.UrlParameter,
    val imageId: Int = 0,
    val selected: Boolean
)