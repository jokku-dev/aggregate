package dev.aggregate.profile

import dev.aggregate.presentation.model.UiAppLanguage
import dev.aggregate.presentation.model.UiText

interface ProfileDataProvider {
    fun provideAppLanguageData(): List<dev.aggregate.app.presentation.model.UiAppLanguage>
}

class ProfileDataProviderFactory : ProfileDataProvider {

    override fun provideAppLanguageData(): List<dev.aggregate.app.presentation.model.UiAppLanguage> {
        return listOf(
            dev.aggregate.app.presentation.model.UiAppLanguage(
                dev.aggregate.app.presentation.model.UiText.StringResource(
                    dev.aggregate.app.R.string.system
                ), true
            ),
            dev.aggregate.app.presentation.model.UiAppLanguage(
                dev.aggregate.app.presentation.model.UiText.StringResource(
                    dev.aggregate.app.R.string.english
                ), false
            ),
            dev.aggregate.app.presentation.model.UiAppLanguage(
                dev.aggregate.app.presentation.model.UiText.StringResource(
                    dev.aggregate.app.R.string.russian
                ), false
            )
        )
    }
}