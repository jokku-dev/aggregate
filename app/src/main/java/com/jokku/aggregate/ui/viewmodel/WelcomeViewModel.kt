package com.jokku.aggregate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.data.DataStoreRepository
import com.jokku.aggregate.ui.data.OnBoardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    val pages = flowOf(
        listOf(OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third)
    )

    fun setLaunchScreen(screen: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.setLaunchScreen(screen = screen)
    }


}