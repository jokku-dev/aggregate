package com.jokku.aggregate.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jokku.aggregate.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _sourceState = MutableStateFlow(SourceState())
    val sourceState = _sourceState.asStateFlow()
}

data class SourceState(
 val sources: List<Source> = emptyList()
)

data class Source(
    val name: String,
    val description: String,
    val url: String,
    val country: String
)