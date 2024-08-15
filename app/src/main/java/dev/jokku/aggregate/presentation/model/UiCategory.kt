package dev.jokku.aggregate.presentation.model

import dev.jokku.aggregate.data.UrlParameter
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UiCategory(
    @Contextual
    val name: UiText,
    @Contextual
    val code: UrlParameter,
    val imageId: Int = 0,
    val selected: Boolean
)