package com.jokku.aggregate.data

import kotlinx.serialization.Serializable

@Serializable
data class ProtoPreferences(
    val languages: List<UrlParameterValue.LanguageCode>,
    val categories: List<UrlParameterValue.Category>,
    val countries: List<UrlParameterValue.CountryCode>
)