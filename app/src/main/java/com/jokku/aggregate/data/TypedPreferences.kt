package com.jokku.aggregate.data

import com.jokku.aggregate.R
import com.jokku.aggregate.ui.entity.Topic
import kotlinx.serialization.Serializable

@Serializable
data class TypedPreferences(
    val topics: List<Topic> = listOf(
        Topic(text = R.string.sports),
        Topic(text = R.string.politics),
        Topic(text = R.string.life),
        Topic(text = R.string.gaming),
        Topic(text = R.string.animals),
        Topic(text = R.string.nature),
        Topic(text = R.string.food),
        Topic(text = R.string.art),
        Topic(text = R.string.history),
        Topic(text = R.string.fashion)
    )
)
