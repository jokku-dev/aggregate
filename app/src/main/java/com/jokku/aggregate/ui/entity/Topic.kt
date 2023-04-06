package com.jokku.aggregate.ui.entity

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    @StringRes val text: Int,
    val selected: Boolean = false
)

