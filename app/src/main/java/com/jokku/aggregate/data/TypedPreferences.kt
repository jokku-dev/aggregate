package com.jokku.aggregate.data

import com.jokku.aggregate.R
import com.jokku.aggregate.ui.entity.Topic
import kotlinx.serialization.Serializable

@Serializable
data class TypedPreferences(
    val topics: List<Topic> = listOf(
        Topic(text = R.string.sports_img),
        Topic(text = R.string.politics_img),
        Topic(text = R.string.life_img),
        Topic(text = R.string.gaming_img),
        Topic(text = R.string.animals_img),
        Topic(text = R.string.nature_img),
        Topic(text = R.string.food_img),
        Topic(text = R.string.art_img),
        Topic(text = R.string.history_img),
        Topic(text = R.string.fashion_img)
    )
)
