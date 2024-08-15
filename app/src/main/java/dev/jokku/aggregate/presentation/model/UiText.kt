package dev.jokku.aggregate.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import kotlin.String

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(@StringRes val id: Int, vararg val args: Any = emptyArray()) : UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, formatArgs = args)
        }
    }
}
