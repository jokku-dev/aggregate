package com.jokku.aggregate.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jokku.aggregate.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    object First : OnBoardingPage(
        R.drawable.img_onboarding,
        R.string.on_board_first_title,
        R.string.on_board_first_description
    )
    object Second : OnBoardingPage(
        R.drawable.img_onboarding,
        R.string.on_board_second_title,
        R.string.on_board_second_description
    )
    object Third : OnBoardingPage(
        R.drawable.img_onboarding,
        R.string.on_board_third_title,
        R.string.on_board_third_description
    )
}
