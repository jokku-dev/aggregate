package dev.aggregate.topheadlines

import dev.aggregate.data.RequestResult
import dev.aggregate.data.map
import dev.aggregate.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopHeadlineArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String): Flow<RequestResult<UiTopHeadlines>> {
        return repository.getTopHeadlines(searchQuery).map { requestResult ->
            requestResult.map { response -> response.toUiTopHeadlines() }
        }
    }
}
