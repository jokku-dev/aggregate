package com.jokku.aggregate.presentation.screens.sources

import com.jokku.aggregate.presentation.screens.BaseNewsViewModel
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
    val sources: List<Source> = emptyList()
)
data class Source(
    val name: String,
    val description: String,
    val url: String,
    val country: String
)