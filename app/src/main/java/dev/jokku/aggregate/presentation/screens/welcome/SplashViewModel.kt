package dev.jokku.aggregate.presentation.screens.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jokku.aggregate.data.local.preferences.PreferencesDataSource
import dev.jokku.aggregate.presentation.nav.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: PreferencesDataSource
): ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readLaunchScreen().collect { state ->
                if (state.isNotEmpty()) _startDestination.value = state
                else _startDestination.value = Screen.OnBoarding.route
            }
            _isLoading.value = false
        }
    }
}