package dev.jokku.ui

import java.util.UUID

data class UiErrorMessage(
    val text: dev.jokku.aggregate.presentation.model.UiText,
    val id: Long = UUID.randomUUID().mostSignificantBits
)
