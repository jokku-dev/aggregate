package com.jokku.aggregate.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UiOnBoardingPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)
