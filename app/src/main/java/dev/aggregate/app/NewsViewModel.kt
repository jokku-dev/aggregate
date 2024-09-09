package dev.aggregate.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.util.TopHeadlinesRequest
import dev.aggregate.ui.UiArticle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface NewsViewModel {
    val articleState: StateFlow<ArticleState>

    fun setChosenArticle(uiArticle: UiArticle)
    fun checkIfArticlesBookmarked(uiArticles: List<UiArticle>)
    fun changeArticleBookmarkedStatus(uiArticle: UiArticle)
    fun getArticlesByRequestParameters(request: TopHeadlinesRequest)
    fun cacheArticles(uiArticles: List<UiArticle>)
}

@HiltViewModel
open class BaseNewsViewModel @Inject constructor() : ViewModel(), NewsViewModel {
    private val _articleState = MutableStateFlow(ArticleState())
    override val articleState = _articleState.asStateFlow()

    override fun setChosenArticle(uiArticle: UiArticle) {
        _articleState.update { state -> state.copy(article = uiArticle) }
    }

    override fun checkIfArticlesBookmarked(uiArticles: List<UiArticle>) {
        TODO("Not yet implemented")
    }

    override fun changeArticleBookmarkedStatus(uiArticle: UiArticle) {
        TODO("Not yet implemented")
    }

    override fun getArticlesByRequestParameters(request: TopHeadlinesRequest) {
        TODO("Not yet implemented")
    }

    override fun cacheArticles(uiArticles: List<UiArticle>) {
        TODO("Not yet implemented")
    }
}

data class ArticleState(
    val article: UiArticle = UiArticle()
)
