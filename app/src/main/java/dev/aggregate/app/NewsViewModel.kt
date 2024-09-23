package dev.aggregate.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.util.TopHeadlinesRequest
import dev.aggregate.model.ui.UiArticle
import javax.inject.Inject

interface NewsViewModel {
    fun checkIfArticlesBookmarked(uiArticles: List<dev.aggregate.model.ui.UiArticle>)
    fun getArticlesByRequestParameters(request: TopHeadlinesRequest)
    fun cacheArticles(uiArticles: List<dev.aggregate.model.ui.UiArticle>)
}

@HiltViewModel
open class BaseNewsViewModel @Inject constructor() : ViewModel(), NewsViewModel {

    override fun checkIfArticlesBookmarked(uiArticles: List<dev.aggregate.model.ui.UiArticle>) {
        TODO("Not yet implemented")
    }

    override fun getArticlesByRequestParameters(request: TopHeadlinesRequest) {
        TODO("Not yet implemented")
    }

    override fun cacheArticles(uiArticles: List<dev.aggregate.model.ui.UiArticle>) {
        TODO("Not yet implemented")
    }
}
