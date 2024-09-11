package dev.aggregate.topheadlines

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

// we promise that properties of this class are immutable (but still can be mutable)
// and therefore if this class equals to new instance of it,
// composable where it's been passed won't be recomposed
@Stable
sealed class TopHeadlinesState(open val stateData: UiTopHeadlines?) {

    @Immutable
    data object None : TopHeadlinesState(stateData = null)

    @Stable
    class Loading(stateData: UiTopHeadlines? = null) : TopHeadlinesState(stateData)

    @Stable
    class Success(override val stateData: UiTopHeadlines) : TopHeadlinesState(stateData)

    @Stable
    class Failure(stateData: UiTopHeadlines? = null) : TopHeadlinesState(stateData)
}
