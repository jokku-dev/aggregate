package dev.jokku.aggregate

import androidx.lifecycle.ViewModel
import dev.jokku.ui.old.TopHeadlinesRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


interface NewsViewModel {
    val articleState: StateFlow<ArticleState>

    fun setChosenArticle(uiArticle: dev.jokku.ui.UiArticle)
    fun checkIfArticlesBookmarked(uiArticles: List<dev.jokku.ui.UiArticle>)
    fun changeArticleBookmarkedStatus(uiArticle: dev.jokku.ui.UiArticle)
    fun getArticlesByRequestParameters(request: TopHeadlinesRequest)
    fun cacheArticles(uiArticles: List<dev.jokku.ui.UiArticle>)
}

@HiltViewModel
open class BaseNewsViewModel @Inject constructor(

) : ViewModel(), NewsViewModel {
    private val _articleState = MutableStateFlow(ArticleState())
    override val articleState = _articleState.asStateFlow()

    override fun setChosenArticle(uiArticle: dev.jokku.ui.UiArticle) {
        _articleState.update { state -> state.copy(article = uiArticle) }
    }

    override fun checkIfArticlesBookmarked(uiArticles: List<dev.jokku.ui.UiArticle>) {
        TODO("Not yet implemented")
    }

    override fun changeArticleBookmarkedStatus(uiArticle: dev.jokku.ui.UiArticle) {
        TODO("Not yet implemented")
    }

    override fun getArticlesByRequestParameters(request: TopHeadlinesRequest) {
        TODO("Not yet implemented")
    }

    override fun cacheArticles(uiArticles: List<dev.jokku.ui.UiArticle>) {
        TODO("Not yet implemented")
    }
}


data class ArticleState(
    val article: dev.jokku.ui.UiArticle = dev.jokku.ui.UiArticle()
)
