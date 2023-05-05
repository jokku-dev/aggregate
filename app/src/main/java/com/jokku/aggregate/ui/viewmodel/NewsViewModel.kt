package com.jokku.aggregate.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.R
import com.jokku.aggregate.data.repo.DataStoreRepository
import com.jokku.aggregate.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface NewsViewModel {
    val homeState: StateFlow<HomeState>
    val sourceState: StateFlow<SourceState>
    val bookmarksState: StateFlow<BookmarksState>
    val articleState: StateFlow<ArticleState>

    fun getSignInStatus(): Job
    fun selectCategory(selected: Category): Job
    fun setChosenArticle(article: Article)
}

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), NewsViewModel {

    private val _homeState = MutableStateFlow(HomeState())
    override val homeState = _homeState.asStateFlow()

    private val _sourceState = MutableStateFlow(SourceState())
    override val sourceState = _sourceState.asStateFlow()

    private val _bookmarksState = MutableStateFlow(BookmarksState())
    override val bookmarksState = _bookmarksState.asStateFlow()

    private val _articleState = MutableStateFlow(ArticleState())
    override val articleState = _articleState.asStateFlow()

    override fun getSignInStatus() = viewModelScope.launch(dispatcher) {
        dataStoreRepository.readUserLoggedIn().collect { logged ->
            _homeState.update { it.copy(loggedIn = logged) }
        }
    }

    override fun selectCategory(selected: Category) = viewModelScope.launch(dispatcher) {
        val categories = _homeState.value.categories.map { category ->
            category.copy(selected = category == selected)
        }
        _homeState.update { state -> state.copy(categories = categories) }
    }

    override fun setChosenArticle(article: Article) {
        _articleState.update { state -> state.copy(article = article) }
    }
}

data class HomeState(
    val loggedIn: Boolean = false,
    val categories: List<Category> = listOf(
        Category(text = R.string.random, selected = true),
        Category(text = R.string.business),
        Category(text = R.string.entertainment),
        Category(text = R.string.general),
        Category(text = R.string.health),
        Category(text = R.string.science),
        Category(text = R.string.sports),
        Category(text = R.string.technology)
    ),
    val articles: List<Article> = emptyList()
)

data class Category(
    val text: Int,
    var selected: Boolean = false
)

data class Article(
    val sourceName: String = "The Washington Post",
    val author: String = "Oliver Darcy",
    val title: String = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
    val description: String = "",
    val url: String = "",
    @DrawableRes val image: Int = R.drawable.img_news_mock_1,
    val publishedAt: String = "",
    val bookmarked: Boolean = true
)

data class BookmarksState(
    val bookmarkedArticles: List<Article> = listOf(
        Article(
            sourceName = "CNN",
            author = "Oliver Darcy",
            title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
            url = "",
            image = R.drawable.img_news_mock_1,
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        ),
        Article(
            sourceName = "CNN",
            author = "Oliver Darcy",
            title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
            url = "",
            image = R.drawable.img_news_mock_1,
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        ),
        Article(
            sourceName = "CNN",
            author = "Oliver Darcy",
            title = "Fox News' sudden firing of Tucker Carlson may have come down to one simple calculation - CNN",
            url = "",
            image = R.drawable.img_news_mock_1,
            publishedAt = "2023-04-25T08:36",
            bookmarked = false
        )
    )
)

data class SourceState(
    val sources: List<Source> = emptyList()
)
data class Source(
    val name: String,
    val description: String,
    val url: String,
    val country: String
)

data class ArticleState(
    val article: Article = Article()
)