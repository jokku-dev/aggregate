package dev.aggregate.ui

import java.util.UUID

data class UiErrorMessage(
    val text: UiText,
    val id: Long = UUID.randomUUID().mostSignificantBits
)
