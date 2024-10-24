package dev.aggregate.topheadlines

import dev.aggregate.model.ui.UiArticle
import kotlinx.collections.immutable.ImmutableList

sealed class TopHeadlinesState(open val articles: ImmutableList<UiArticle>?) {
    data object None : TopHeadlinesState(articles = null)
    class InProgress(stateData: ImmutableList<UiArticle>? = null) : TopHeadlinesState(stateData)
    class Success(override val articles: ImmutableList<UiArticle>) : TopHeadlinesState(articles)
    class Error(stateData: ImmutableList<UiArticle>? = null) : TopHeadlinesState(stateData)
}
