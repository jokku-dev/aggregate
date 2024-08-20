package dev.jokku.topheadlines

import dev.jokku.newsdata.ArticlesRepository
import dev.jokku.newsdata.model.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticlesRepository) {

    operator fun invoke(): Flow<Article> {
        return repository.getAll()
    }
}