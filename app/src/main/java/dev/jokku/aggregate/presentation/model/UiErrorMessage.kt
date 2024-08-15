package dev.jokku.aggregate.presentation.model

import dev.jokku.aggregate.presentation.model.UiText
import java.util.UUID

data class UiErrorMessage(
    val text: UiText,
    val id: Long = UUID.randomUUID().mostSignificantBits
)
