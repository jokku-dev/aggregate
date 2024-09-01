package dev.jokku.topheadlines

import dev.jokku.model.ArticlesResponse

sealed class TopHeadlinesViewModelState(open val stateData: UiTopHeadlines?) {
    data object None : TopHeadlinesViewModelState(stateData = null)

    class Loading(stateData: UiTopHeadlines? = null) : TopHeadlinesViewModelState(stateData)

    class Success(override val stateData: UiTopHeadlines) : TopHeadlinesViewModelState(stateData)

    class Failure(stateData: UiTopHeadlines? = null) : TopHeadlinesViewModelState(stateData)
}