package dev.jokku.aggregate.presentation

import dev.jokku.aggregate.domain.Result

interface State<out T> {
    object Loading : State<Nothing>
    data class Success<out T>(val data: T) : State<T>
}