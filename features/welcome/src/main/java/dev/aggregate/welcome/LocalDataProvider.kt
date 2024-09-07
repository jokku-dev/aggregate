package dev.aggregate.welcome

import dev.aggregate.app.R
import dev.aggregate.data.CountryCode
import dev.aggregate.presentation.model.UiCategory
import dev.aggregate.presentation.model.UiOnBoardingPage
import dev.aggregate.presentation.model.UiText

enum class CategoryType {
    LANGUAGE, CATEGORY, COUNTRY
}

interface LocalDataProvider {
    fun provideNewsCategoriesPreferences(type: CategoryType): List<dev.aggregate.app.presentation.model.UiCategory>
    fun provideOnBoardingPages(): List<dev.aggregate.app.presentation.model.UiOnBoardingPage>
    fun provideCategoryNameByCode(
        type: CategoryType,
        code: dev.aggregate.app.data.UrlParameter
    ): dev.aggregate.app.presentation.model.UiText
}

class LocalDataProviderFactory : LocalDataProvider {

    override fun provideCategoryNameByCode(
        type: CategoryType,
        code: dev.aggregate.app.data.UrlParameter
    ): dev.aggregate.app.presentation.model.UiText {
        return provideNewsCategoriesPreferences(type)
            .find { uiCategory ->
                uiCategory.code == code
            }?.name
            ?: dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.unknown_category)
    }

    override fun provideNewsCategoriesPreferences(type: CategoryType): List<dev.aggregate.app.presentation.model.UiCategory> {
        return when (type) {
            CategoryType.LANGUAGE -> listOf(
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.arabic),
                    code = dev.aggregate.app.data.LanguageCode.AR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.german),
                    code = dev.aggregate.app.data.LanguageCode.DE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.english),
                    code = dev.aggregate.app.data.LanguageCode.EN,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.spanish),
                    code = dev.aggregate.app.data.LanguageCode.ES,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.french),
                    code = dev.aggregate.app.data.LanguageCode.FR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.hebrew),
                    code = dev.aggregate.app.data.LanguageCode.HE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.italian),
                    code = dev.aggregate.app.data.LanguageCode.IT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.dutch),
                    code = dev.aggregate.app.data.LanguageCode.NL,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.norwegian),
                    code = dev.aggregate.app.data.LanguageCode.NO,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.portuguese),
                    code = dev.aggregate.app.data.LanguageCode.PT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.russian),
                    code = dev.aggregate.app.data.LanguageCode.RU,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.swedish),
                    code = dev.aggregate.app.data.LanguageCode.SV,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.chinese),
                    code = dev.aggregate.app.data.LanguageCode.ZH,
                    selected = false
                ),
            )

            CategoryType.CATEGORY -> listOf(
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.business),
                    code = dev.aggregate.app.data.CategoryCode.BUSINESS,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.entertainment),
                    code = dev.aggregate.app.data.CategoryCode.ENTERTAINMENT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.general),
                    code = dev.aggregate.app.data.CategoryCode.GENERAL,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.health),
                    code = dev.aggregate.app.data.CategoryCode.HEALTH,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.science),
                    code = dev.aggregate.app.data.CategoryCode.SCIENCE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.sports),
                    code = dev.aggregate.app.data.CategoryCode.SPORTS,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.technology),
                    code = dev.aggregate.app.data.CategoryCode.TECHNOLOGY,
                    selected = false
                ),
            )

            CategoryType.COUNTRY -> listOf(
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.united_arab_emirates),
                    code = dev.aggregate.app.data.CountryCode.AE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.argentina),
                    code = dev.aggregate.app.data.CountryCode.AR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.austria),
                    code = dev.aggregate.app.data.CountryCode.AT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.australia),
                    code = dev.aggregate.app.data.CountryCode.AU,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.belgium),
                    code = dev.aggregate.app.data.CountryCode.BE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.bulgaria),
                    code = dev.aggregate.app.data.CountryCode.BG,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.brazil),
                    code = dev.aggregate.app.data.CountryCode.BR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.canada),
                    code = dev.aggregate.app.data.CountryCode.CA,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.switzerland),
                    code = dev.aggregate.app.data.CountryCode.CH,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.china),
                    code = dev.aggregate.app.data.CountryCode.CN,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.colombia),
                    code = dev.aggregate.app.data.CountryCode.CO,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.cuba),
                    code = dev.aggregate.app.data.CountryCode.CU,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.czechia),
                    code = dev.aggregate.app.data.CountryCode.CZ,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.germany),
                    code = dev.aggregate.app.data.CountryCode.DE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.egypt),
                    code = dev.aggregate.app.data.CountryCode.EG,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.france),
                    code = dev.aggregate.app.data.CountryCode.FR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.united_kingdom),
                    code = dev.aggregate.app.data.CountryCode.GB,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.greece),
                    code = dev.aggregate.app.data.CountryCode.GR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.hong_kong),
                    code = dev.aggregate.app.data.CountryCode.HK,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.hungary),
                    code = dev.aggregate.app.data.CountryCode.HU,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.indonesia),
                    code = dev.aggregate.app.data.CountryCode.ID,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.ireland),
                    code = dev.aggregate.app.data.CountryCode.IE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.israel),
                    code = dev.aggregate.app.data.CountryCode.IL,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.india),
                    code = dev.aggregate.app.data.CountryCode.IN,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.italy),
                    code = dev.aggregate.app.data.CountryCode.IT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.japan),
                    code = dev.aggregate.app.data.CountryCode.JP,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.republic_of_korea),
                    code = dev.aggregate.app.data.CountryCode.KR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.lithuania),
                    code = dev.aggregate.app.data.CountryCode.LT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.latvia),
                    code = dev.aggregate.app.data.CountryCode.LV,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.morocco),
                    code = dev.aggregate.app.data.CountryCode.MA,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.mexico),
                    code = dev.aggregate.app.data.CountryCode.MX,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.malaysia),
                    code = dev.aggregate.app.data.CountryCode.MY,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.nigeria),
                    code = dev.aggregate.app.data.CountryCode.NG,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.netherlands),
                    code = dev.aggregate.app.data.CountryCode.NL,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.norway),
                    code = dev.aggregate.app.data.CountryCode.NO,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.new_zealand),
                    code = dev.aggregate.app.data.CountryCode.NZ,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.philippines),
                    code = dev.aggregate.app.data.CountryCode.PH,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.poland),
                    code = dev.aggregate.app.data.CountryCode.PL,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.portugal),
                    code = dev.aggregate.app.data.CountryCode.PT,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.romania),
                    code = dev.aggregate.app.data.CountryCode.RO,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.serbia),
                    code = dev.aggregate.app.data.CountryCode.RS,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.russian_federation),
                    code = dev.aggregate.app.data.CountryCode.RU,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.saudi_arabia),
                    code = dev.aggregate.app.data.CountryCode.SA,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.sweden),
                    code = dev.aggregate.app.data.CountryCode.SE,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.singapore),
                    code = dev.aggregate.app.data.CountryCode.SG,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.slovenia),
                    code = dev.aggregate.app.data.CountryCode.SI,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.slovakia),
                    code = dev.aggregate.app.data.CountryCode.SK,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.thailand),
                    code = dev.aggregate.app.data.CountryCode.TH,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.turkey),
                    code = dev.aggregate.app.data.CountryCode.TR,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.taiwan),
                    code = dev.aggregate.app.data.CountryCode.TW,
                    selected = false
                ),
                dev.aggregate.app.presentation.model.UiCategory(
                    name = dev.aggregate.app.presentation.model.UiText.StringResource(dev.aggregate.app.R.string.ukraine),
                    code = dev.aggregate.app.data.CountryCode.UA,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.united_states_of_america),
                    code = CountryCode.US,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.venezuela),
                    code = CountryCode.VE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.south_africa),
                    code = CountryCode.ZA,
                    selected = false
                )
            )
        }
    }

    override fun provideOnBoardingPages(): List<UiOnBoardingPage> {
        return listOf(
            UiOnBoardingPage(
                R.drawable.img_onboarding,
                R.string.on_board_first_title,
                R.string.on_board_first_description
            ),
            UiOnBoardingPage(
                R.drawable.img_onboarding,
                R.string.on_board_second_title,
                R.string.on_board_second_description
            ),
            UiOnBoardingPage(
                R.drawable.img_onboarding,
                R.string.on_board_third_title,
                R.string.on_board_third_description
            )
        )
    }
}