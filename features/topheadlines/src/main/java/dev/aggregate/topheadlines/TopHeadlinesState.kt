package dev.aggregate.topheadlines

sealed class TopHeadlinesState(open val stateData: UiTopHeadlines?) {
    data object None : TopHeadlinesState(stateData = null)

    class Loading(stateData: UiTopHeadlines? = null) : TopHeadlinesState(stateData)

    class Success(override val stateData: UiTopHeadlines) : TopHeadlinesState(stateData)

    class Failure(stateData: UiTopHeadlines? = null) : TopHeadlinesState(stateData)
}