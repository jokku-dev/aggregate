package com.jokku.aggregate.data.store

import com.jokku.aggregate.data.UrlParameterValue
import com.jokku.aggregate.presentation.model.UiCategory
import kotlinx.serialization.Serializable

@Serializable
data class ProtoModel(
    val languages: List<UiCategory> = emptyList(),
    val categories: List<UiCategory> = emptyList(),
    val countries: List<UiCategory> = emptyList()
)