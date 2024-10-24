package dev.aggregate.profile

import dev.aggregate.model.ui.UiAppLanguage
import dev.aggregate.model.ui.UiText

interface ProfileDataProvider {
    fun provideAppLanguageData(): List<UiAppLanguage>
}

class ProfileDataProviderFactory : ProfileDataProvider {

    override fun provideAppLanguageData(): List<UiAppLanguage> {
        return listOf(
            UiAppLanguage(
                UiText.StringResource(R.string.system),
                true
            ),
            UiAppLanguage(
                UiText.StringResource(R.string.english),
                false
            ),
            UiAppLanguage(
                UiText.StringResource(R.string.russian),
                false
            )
        )
    }
}
