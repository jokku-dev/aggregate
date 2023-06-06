package com.jokku.aggregate.presentation.model

import com.jokku.aggregate.data.UrlParameterValue
import kotlinx.serialization.Serializable

@Serializable
data class UiCategory(
    val name: UiText,
    val code: UrlParameterValue,
    val imageId: Int = 0,
    val selected: Boolean
)