package dev.jokku.aggregate.ui

interface State<out T> {
    object Loading : State<Nothing>
    data class Success<out T>(val data: T) : State<T>
}