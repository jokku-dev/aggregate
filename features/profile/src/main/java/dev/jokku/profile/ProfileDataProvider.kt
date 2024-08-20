package dev.jokku.profile

import dev.jokku.aggregate.presentation.model.UiAppLanguage
import dev.jokku.aggregate.presentation.model.UiText

interface ProfileDataProvider {
    fun provideAppLanguageData(): List<dev.jokku.aggregate.presentation.model.UiAppLanguage>
}

class ProfileDataProviderFactory : ProfileDataProvider {

    override fun provideAppLanguageData(): List<dev.jokku.aggregate.presentation.model.UiAppLanguage> {
        return listOf(
            dev.jokku.aggregate.presentation.model.UiAppLanguage(
                dev.jokku.aggregate.presentation.model.UiText.StringResource(
                    dev.jokku.aggregate.R.string.system
                ), true
            ),
            dev.jokku.aggregate.presentation.model.UiAppLanguage(
                dev.jokku.aggregate.presentation.model.UiText.StringResource(
                    dev.jokku.aggregate.R.string.english
                ), false
            ),
            dev.jokku.aggregate.presentation.model.UiAppLanguage(
                dev.jokku.aggregate.presentation.model.UiText.StringResource(
                    dev.jokku.aggregate.R.string.russian
                ), false
            )
        )
    }
}