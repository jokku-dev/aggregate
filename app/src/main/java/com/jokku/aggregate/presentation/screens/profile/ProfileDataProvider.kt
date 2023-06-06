package com.jokku.aggregate.presentation.screens.profile

import com.jokku.aggregate.R
import com.jokku.aggregate.presentation.model.UiAppLanguage
import com.jokku.aggregate.presentation.model.UiText

interface ProfileDataProvider {
    fun provideAppLanguageData(): List<UiAppLanguage>
}

class ProfileDataProviderFactory : ProfileDataProvider {

    override fun provideAppLanguageData(): List<UiAppLanguage> {
        return listOf(
            UiAppLanguage(UiText.StringResource(R.string.system), true),
            UiAppLanguage(UiText.StringResource(R.string.english), false),
            UiAppLanguage(UiText.StringResource(R.string.russian), false)
        )
    }
}