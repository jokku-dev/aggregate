package dev.aggregate.topheadlines

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import dev.aggregate.model.ui.UiArticle

// we promise that properties of this class are immutable (but still can be mutable)
// and therefore if this class equals to new instance of it,
// composable where it's been passed won't be recomposed
@Stable
sealed class TopHeadlinesState(open val stateData: List<UiArticle>?) {

    @Immutable
    data object None : TopHeadlinesState(stateData = null)

    @Stable
    class Loading(stateData: List<UiArticle>? = null) : TopHeadlinesState(stateData)

    @Stable
    class Success(override val stateData: List<UiArticle>) : TopHeadlinesState(stateData)

    @Stable
    class Failure(stateData: List<UiArticle>? = null) : TopHeadlinesState(stateData)
}
