package dev.jokku.welcome

import dev.jokku.aggregate.R
import dev.jokku.data.CountryCode
import dev.jokku.aggregate.presentation.model.UiOnBoardingPage
import dev.jokku.aggregate.presentation.model.UiCategory
import dev.jokku.aggregate.presentation.model.UiText
import kotlin.collections.find

enum class CategoryType {
    LANGUAGE, CATEGORY, COUNTRY
}

interface LocalDataProvider {
    fun provideNewsCategoriesPreferences(type: CategoryType): List<dev.jokku.aggregate.presentation.model.UiCategory>
    fun provideOnBoardingPages(): List<dev.jokku.aggregate.presentation.model.UiOnBoardingPage>
    fun provideCategoryNameByCode(type: CategoryType, code: dev.jokku.data.UrlParameter): dev.jokku.aggregate.presentation.model.UiText
}

class LocalDataProviderFactory : LocalDataProvider {

    override fun provideCategoryNameByCode(type: CategoryType, code: dev.jokku.data.UrlParameter): dev.jokku.aggregate.presentation.model.UiText {
        return provideNewsCategoriesPreferences(type)
            .find { uiCategory ->
                uiCategory.code == code
            }?.name ?: dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.unknown_category)
    }

    override fun provideNewsCategoriesPreferences(type: CategoryType): List<dev.jokku.aggregate.presentation.model.UiCategory> {
        return when (type) {
            CategoryType.LANGUAGE -> listOf(
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.arabic),
                    code = dev.jokku.data.LanguageCode.AR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.german),
                    code = dev.jokku.data.LanguageCode.DE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.english),
                    code = dev.jokku.data.LanguageCode.EN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.spanish),
                    code = dev.jokku.data.LanguageCode.ES,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.french),
                    code = dev.jokku.data.LanguageCode.FR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hebrew),
                    code = dev.jokku.data.LanguageCode.HE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.italian),
                    code = dev.jokku.data.LanguageCode.IT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.dutch),
                    code = dev.jokku.data.LanguageCode.NL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.norwegian),
                    code = dev.jokku.data.LanguageCode.NO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.portuguese),
                    code = dev.jokku.data.LanguageCode.PT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.russian),
                    code = dev.jokku.data.LanguageCode.RU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.swedish),
                    code = dev.jokku.data.LanguageCode.SV,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.chinese),
                    code = dev.jokku.data.LanguageCode.ZH,
                    selected = false
                ),
            )

            CategoryType.CATEGORY -> listOf(
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.business),
                    code = dev.jokku.data.CategoryCode.BUSINESS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.entertainment),
                    code = dev.jokku.data.CategoryCode.ENTERTAINMENT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.general),
                    code = dev.jokku.data.CategoryCode.GENERAL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.health),
                    code = dev.jokku.data.CategoryCode.HEALTH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.science),
                    code = dev.jokku.data.CategoryCode.SCIENCE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.sports),
                    code = dev.jokku.data.CategoryCode.SPORTS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.technology),
                    code = dev.jokku.data.CategoryCode.TECHNOLOGY,
                    selected = false
                ),
            )

            CategoryType.COUNTRY -> listOf(
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.united_arab_emirates),
                    code = dev.jokku.data.CountryCode.AE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.argentina),
                    code = dev.jokku.data.CountryCode.AR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.austria),
                    code = dev.jokku.data.CountryCode.AT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.australia),
                    code = dev.jokku.data.CountryCode.AU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.belgium),
                    code = dev.jokku.data.CountryCode.BE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.bulgaria),
                    code = dev.jokku.data.CountryCode.BG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.brazil),
                    code = dev.jokku.data.CountryCode.BR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.canada),
                    code = dev.jokku.data.CountryCode.CA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.switzerland),
                    code = dev.jokku.data.CountryCode.CH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.china),
                    code = dev.jokku.data.CountryCode.CN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.colombia),
                    code = dev.jokku.data.CountryCode.CO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.cuba),
                    code = dev.jokku.data.CountryCode.CU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.czechia),
                    code = dev.jokku.data.CountryCode.CZ,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.germany),
                    code = dev.jokku.data.CountryCode.DE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.egypt),
                    code = dev.jokku.data.CountryCode.EG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.france),
                    code = dev.jokku.data.CountryCode.FR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.united_kingdom),
                    code = dev.jokku.data.CountryCode.GB,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.greece),
                    code = dev.jokku.data.CountryCode.GR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hong_kong),
                    code = dev.jokku.data.CountryCode.HK,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hungary),
                    code = dev.jokku.data.CountryCode.HU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.indonesia),
                    code = dev.jokku.data.CountryCode.ID,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.ireland),
                    code = dev.jokku.data.CountryCode.IE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.israel),
                    code = dev.jokku.data.CountryCode.IL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.india),
                    code = dev.jokku.data.CountryCode.IN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.italy),
                    code = dev.jokku.data.CountryCode.IT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.japan),
                    code = dev.jokku.data.CountryCode.JP,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.republic_of_korea),
                    code = dev.jokku.data.CountryCode.KR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.lithuania),
                    code = dev.jokku.data.CountryCode.LT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.latvia),
                    code = dev.jokku.data.CountryCode.LV,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.morocco),
                    code = dev.jokku.data.CountryCode.MA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.mexico),
                    code = dev.jokku.data.CountryCode.MX,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.malaysia),
                    code = dev.jokku.data.CountryCode.MY,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.nigeria),
                    code = dev.jokku.data.CountryCode.NG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.netherlands),
                    code = dev.jokku.data.CountryCode.NL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.norway),
                    code = dev.jokku.data.CountryCode.NO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.new_zealand),
                    code = dev.jokku.data.CountryCode.NZ,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.philippines),
                    code = dev.jokku.data.CountryCode.PH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.poland),
                    code = dev.jokku.data.CountryCode.PL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.portugal),
                    code = dev.jokku.data.CountryCode.PT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.romania),
                    code = dev.jokku.data.CountryCode.RO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.serbia),
                    code = dev.jokku.data.CountryCode.RS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.russian_federation),
                    code = dev.jokku.data.CountryCode.RU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.saudi_arabia),
                    code = dev.jokku.data.CountryCode.SA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.sweden),
                    code = dev.jokku.data.CountryCode.SE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.singapore),
                    code = dev.jokku.data.CountryCode.SG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.slovenia),
                    code = dev.jokku.data.CountryCode.SI,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.slovakia),
                    code = dev.jokku.data.CountryCode.SK,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.thailand),
                    code = dev.jokku.data.CountryCode.TH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.turkey),
                    code = dev.jokku.data.CountryCode.TR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.taiwan),
                    code = dev.jokku.data.CountryCode.TW,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.ukraine),
                    code = dev.jokku.data.CountryCode.UA,
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