package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.model.Article
import dev.aggregate.model.ArticlesResponse
import dev.aggregate.ui.UiArticle

internal fun RequestResult<ArticlesResponse>.toState(): TopHeadlinesState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesState.Failure(data as UiTopHeadlines?)
        is RequestResult.InProgress -> TopHeadlinesState.Loading(data as UiTopHeadlines?)
        is RequestResult.Success -> TopHeadlinesState.Success(data)
    }
}

internal fun ArticlesResponse.toUiTopHeadlines() = UiTopHeadlines()

internal fun Article.toUiArticle() = UiArticle()