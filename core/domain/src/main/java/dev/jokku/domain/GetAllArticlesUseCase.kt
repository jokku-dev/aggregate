package dev.jokku.domain

import dev.jokku.data.model.Article
import dev.jokku.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: NewsRepository) {

    operator fun invoke(): Flow<Article> {
        return repository.getAll()
    }
}