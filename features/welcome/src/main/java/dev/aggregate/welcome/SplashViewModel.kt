package dev.aggregate.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.designsystem.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: PreferencesRepository
) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.userData.collect { state ->
                if (state.launchScreen.isNotEmpty()) {
                    _startDestination.value = state.launchScreen
                } else {
                    _startDestination.value = Screen.OnBoarding.route
                }
            }
            _isLoading.value = false
        }
    }
}
