package com.jokku.aggregate.ui.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokku.aggregate.R
import com.jokku.aggregate.data.repo.NewsRepository
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
    val appLanguageState: StateFlow<AppLanguageState>

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

    private val _appLanguageState = MutableStateFlow(AppLanguageState())
    override val appLanguageState = _appLanguageState.asStateFlow()

    override fun changeNotificationsStatus(status: Boolean) = viewModelScope.launch(dispatcher) {

    }

    override fun changeSignInStatus(status: Boolean) = viewModelScope.launch(dispatcher) {

    }

    override fun changeAppLanguage(language: Int) = viewModelScope.launch(dispatcher) {

    }
}

data class ProfileState(
    val userSignedIn: Boolean = false,
    val notificationsSwitchedOn: Boolean = false
)

data class AppLanguageState(
    val languages: List<Language> = listOf(
        Language(R.string.system, true),
        Language(R.string.english, false),
        Language(R.string.russian, false)
    )
)

data class Language(
    @StringRes val language: Int,
    val selected: Boolean = false
)