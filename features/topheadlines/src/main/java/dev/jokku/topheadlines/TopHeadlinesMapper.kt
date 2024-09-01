package dev.jokku.topheadlines

import dev.jokku.data.RequestResult
import dev.jokku.model.Article
import dev.jokku.model.ArticlesResponse
import dev.jokku.ui.UiArticle

internal fun RequestResult<ArticlesResponse>.toState(): TopHeadlinesViewModelState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesViewModelState.Failure(data as UiTopHeadlines?)
        is RequestResult.InProgress -> TopHeadlinesViewModelState.Loading(data as UiTopHeadlines?)
        is RequestResult.Success -> TopHeadlinesViewModelState.Success(data)
    }
}

internal fun ArticlesResponse.toUiTopHeadlines() = UiTopHeadlines()

internal fun Article.toUiArticle() = UiArticle()