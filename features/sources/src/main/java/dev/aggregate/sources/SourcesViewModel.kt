package dev.aggregate.sources

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.model.ui.UiNewsSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

interface SourcesViewModel {
    val sourcesState: StateFlow<SourcesState>
}

@HiltViewModel
class MainSourcesViewModel @Inject constructor(

) : ViewModel(), SourcesViewModel {
    private val _sourcesState = MutableStateFlow(SourcesState())
    override val sourcesState = _sourcesState.asStateFlow()
}

data class SourcesState(
    val sources: List<UiNewsSource> = emptyList()
)
