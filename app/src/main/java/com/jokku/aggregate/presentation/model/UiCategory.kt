package com.jokku.aggregate.presentation.model

import com.jokku.aggregate.data.UrlParameterValue
import com.jokku.aggregate.presentation.model.UiText

data class UiSelectableCategory(
    val name: UiText,
    val code: UrlParameterValue,
    val imageId: Int = 0,
    val selected: Boolean
)