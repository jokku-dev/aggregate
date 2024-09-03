package dev.aggregate.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface SourcesViewModel {
    val sourcesState: StateFlow<SourcesState>
}

@dagger.hilt.android.lifecycle.HiltViewModel
class MainSourcesViewModel @javax.inject.Inject constructor(

) : dev.aggregate.app.BaseNewsViewModel(), SourcesViewModel {
    private val _sourcesState = MutableStateFlow(SourcesState())
    override val sourcesState = _sourcesState.asStateFlow()
}

data class SourcesState(
    val sources: List<dev.aggregate.app.presentation.model.UiNewsSource> = emptyList()
)