package dev.aggregate.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface MainActivityViewModel {
}

@HiltViewModel
open class BaseMainActivityViewModel @Inject constructor() : ViewModel(), MainActivityViewModel {
}
