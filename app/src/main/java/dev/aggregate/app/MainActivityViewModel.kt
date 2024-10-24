package dev.aggregate.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aggregate.app.MainActivityUiState.Loading
import dev.aggregate.app.MainActivityUiState.Success
import dev.aggregate.data.repository.PreferencesRepository
import dev.aggregate.designsystem.Screen
import dev.aggregate.model.UserData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: PreferencesRepository
) : ViewModel() {
//    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
//    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    val uiState: StateFlow<MainActivityUiState> = repository.userData.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    init {
        viewModelScope.launch {
            repository.userData.collect { state ->
                if (state.launchScreen.isNotEmpty()) {
                    _startDestination.value = state.launchScreen
                } else {
                    _startDestination.value = Screen.OnBoarding.route
                }
            }
//            _isLoading.value = false
        }
    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}
