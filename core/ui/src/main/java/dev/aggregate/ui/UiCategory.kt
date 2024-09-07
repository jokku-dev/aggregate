package dev.aggregate.ui

import kotlinx.serialization.Contextual

@Serializable
data class UiCategory(
    @Contextual
    val name: UiText,
    @Contextual
    val code: UrlParameter,
    val imageId: Int = 0,
    val selected: Boolean
)
