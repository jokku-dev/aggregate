package dev.jokku.topheadlines

import dev.jokku.data.RequestResult
import dev.jokku.data.map
import dev.jokku.data.repository.NewsRepository
import dev.jokku.ui.old.TopHeadlinesRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlineArticlesUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(searchQuery: String): Flow<RequestResult<UiTopHeadlines>> {
        return repository.getTopHeadlines(searchQuery).map { requestResult ->
            requestResult.map { response -> response.toUiTopHeadlines() }
        }
    }
}