package com.jokku.aggregate.presentation

import com.jokku.aggregate.domain.Result

interface State<out T> {
    object Loading : State<Nothing>
    data class Success<out T>(val data: T) : State<T>
}