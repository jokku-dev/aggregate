package dev.jokku.aggregate.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jokku.aggregate.R
import dev.jokku.aggregate.data.repo.NewsRepository
import dev.jokku.aggregate.presentation.model.UiAppLanguage
import dev.jokku.aggregate.presentation.model.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ProfileViewModel {
    val profileState: StateFlow<ProfileState>

    fun changeNotificationsStatus(status: Boolean): Job
    fun changeSignInStatus(status: Boolean): Job
    fun changeAppLanguage(language: Int): Job
}

@HiltViewModel
class MainProfileViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), ProfileViewModel {

    private val _profileState = MutableStateFlow(ProfileState())
    override val profileState = _profileState.asStateFlow()

    override fun changeNotificationsStatus(status: Boolean) = viewModelScope.launch(dispatcher) {

    }

    override fun changeSignInStatus(status: Boolean) = viewModelScope.launch(dispatcher) {

    }

    override fun changeAppLanguage(language: Int) = viewModelScope.launch(dispatcher) {

    }
}

data class ProfileState(
    val userSignedIn: Boolean = true,
    val notificationsSwitchedOn: Boolean = false,
    val languages: List<UiAppLanguage> = emptyList()
)