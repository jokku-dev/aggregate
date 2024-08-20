package dev.jokku.ui

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import kotlin.String

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(@StringRes val id: Int, vararg val args: Any = emptyArray()) : UiText()

    @androidx.compose.runtime.Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> androidx.compose.ui.res.stringResource(id = id, formatArgs = args)
        }
    }
}
