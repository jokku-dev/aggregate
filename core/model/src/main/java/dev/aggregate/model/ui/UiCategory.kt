package dev.aggregate.model.ui

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UiCategory(
    @Contextual
    val name: UiText,
    @Contextual
    val code: String,
    val imageId: Int = 0,
    val selected: Boolean
)
