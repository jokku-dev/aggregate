package dev.jokku.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface SourcesViewModel {
    val sourcesState: StateFlow<SourcesState>
}

@dagger.hilt.android.lifecycle.HiltViewModel
class MainSourcesViewModel @javax.inject.Inject constructor(

) : dev.jokku.aggregate.BaseNewsViewModel(), SourcesViewModel {
    private val _sourcesState = MutableStateFlow(SourcesState())
    override val sourcesState = _sourcesState.asStateFlow()
}

data class SourcesState(
    val sources: List<dev.jokku.aggregate.presentation.model.UiNewsSource> = emptyList()
)