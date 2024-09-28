package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.model.ArticlesResponse
import dev.aggregate.model.UserData
import dev.aggregate.model.ui.UiArticle
import dev.aggregate.model.ui.toUiArticles

internal fun RequestResult<UiTopHeadlines>.toState(): TopHeadlinesState {
    return when (this) {
        is RequestResult.Error -> TopHeadlinesState.Failure(data)
        is RequestResult.InProgress -> TopHeadlinesState.Loading(data)
        is RequestResult.Success -> TopHeadlinesState.Success(data)
    }
}

internal fun ArticlesResponse.toUiTopHeadlines(
    selectedArticle: UiArticle,
    userData: UserData
) = UiTopHeadlines(
    articles = articles.toUiArticles(userData),
    selectedArticle = selectedArticle
)
