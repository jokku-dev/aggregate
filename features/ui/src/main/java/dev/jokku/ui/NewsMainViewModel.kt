package dev.jokku.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class NewsMainViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(State.None)
    val articles: StateFlow<State>
        get() = _state.asStateFlow() // get - is to avoid saving the link
}

sealed class State {
    object None : State()
    class Loading : State()
    class Error : State()
    class Success(val articleUis: List<ArticleUi>) : State()
}