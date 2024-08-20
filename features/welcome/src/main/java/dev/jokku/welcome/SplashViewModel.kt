package dev.jokku.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jokku.aggregate.presentation.navigation.Screen
import kotlinx.coroutines.launch

class SplashViewModel @javax.inject.Inject constructor(
    private val repository: dev.jokku.aggregate.data.local.preferences.PreferencesDataSource
): ViewModel() {
    private val _isLoading: androidx.compose.runtime.MutableState<Boolean> =
        androidx.compose.runtime.mutableStateOf(true)
    val isLoading: androidx.compose.runtime.State<Boolean> = _isLoading

    private val _startDestination: androidx.compose.runtime.MutableState<String> =
        androidx.compose.runtime.mutableStateOf(dev.jokku.aggregate.presentation.navigation.Screen.OnBoarding.route)
    val startDestination: androidx.compose.runtime.State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readLaunchScreen().collect { state ->
                if (state.isNotEmpty()) _startDestination.value = state
                else _startDestination.value = dev.jokku.aggregate.presentation.navigation.Screen.OnBoarding.route
            }
            _isLoading.value = false
        }
    }
}