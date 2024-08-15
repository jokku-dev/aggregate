package dev.jokku.aggregate.presentation.screens.sources

import dev.jokku.aggregate.presentation.model.UiNewsSource
import dev.jokku.aggregate.presentation.screens.BaseNewsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface SourcesViewModel {
    val sourcesState: StateFlow<SourcesState>
}

@HiltViewModel
class MainSourcesViewModel @Inject constructor(

) : BaseNewsViewModel(), SourcesViewModel {
    private val _sourcesState = MutableStateFlow(SourcesState())
    override val sourcesState = _sourcesState.asStateFlow()
}

data class SourcesState(
    val sources: List<UiNewsSource> = emptyList()
)