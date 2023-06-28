package com.jokku.aggregate.presentation.model

import com.jokku.aggregate.data.UrlParameter
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