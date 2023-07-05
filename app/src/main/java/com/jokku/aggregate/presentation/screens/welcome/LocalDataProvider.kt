package com.jokku.aggregate.presentation.screens.welcome

import com.jokku.aggregate.R
import com.jokku.aggregate.data.CategoryCode
import com.jokku.aggregate.data.CountryCode
import com.jokku.aggregate.data.LanguageCode
import com.jokku.aggregate.data.UrlParameter
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.presentation.model.UiCategory
import com.jokku.aggregate.presentation.model.UiText

enum class CategoryType {
    LANGUAGE, CATEGORY, COUNTRY
}

interface LocalDataProvider {
    fun provideNewsCategoriesPreferences(type: CategoryType): List<UiCategory>
    fun provideOnBoardingPages(): List<UiOnBoardingPage>
    fun provideCategoryNameByCode(type: CategoryType, code: UrlParameter): UiText
}

class LocalDataProviderFactory : LocalDataProvider {

    override fun provideCategoryNameByCode(type: CategoryType, code: UrlParameter): UiText {
        return provideNewsCategoriesPreferences(type)
            .find { uiCategory ->
                uiCategory.code == code
            }?.name ?: UiText.StringResource(R.string.unknown_category)
    }

    override fun provideNewsCategoriesPreferences(type: CategoryType): List<UiCategory> {
        return when (type) {
            CategoryType.LANGUAGE -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.arabic),
                    code = LanguageCode.AR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.german),
                    code = LanguageCode.DE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.english),
                    code = LanguageCode.EN,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.spanish),
                    code = LanguageCode.ES,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.french),
                    code = LanguageCode.FR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hebrew),
                    code = LanguageCode.HE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.italian),
                    code = LanguageCode.IT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.dutch),
                    code = LanguageCode.NL,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.norwegian),
                    code = LanguageCode.NO,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.portuguese),
                    code = LanguageCode.PT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.russian),
                    code = LanguageCode.RU,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.swedish),
                    code = LanguageCode.SV,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.chinese),
                    code = LanguageCode.ZH,
                    selected = false
                ),
            )

            CategoryType.CATEGORY -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.business),
                    code = CategoryCode.BUSINESS,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.entertainment),
                    code = CategoryCode.ENTERTAINMENT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.general),
                    code = CategoryCode.GENERAL,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.health),
                    code = CategoryCode.HEALTH,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.science),
                    code = CategoryCode.SCIENCE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.sports),
                    code = CategoryCode.SPORTS,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.technology),
                    code = CategoryCode.TECHNOLOGY,
                    selected = false
                ),
            )

            CategoryType.COUNTRY -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.united_arab_emirates),
                    code = CountryCode.AE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.argentina),
                    code = CountryCode.AR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.austria),
                    code = CountryCode.AT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.australia),
                    code = CountryCode.AU,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.belgium),
                    code = CountryCode.BE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.bulgaria),
                    code = CountryCode.BG,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.brazil),
                    code = CountryCode.BR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.canada),
                    code = CountryCode.CA,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.switzerland),
                    code = CountryCode.CH,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.china),
                    code = CountryCode.CN,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.colombia),
                    code = CountryCode.CO,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.cuba),
                    code = CountryCode.CU,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.czechia),
                    code = CountryCode.CZ,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.germany),
                    code = CountryCode.DE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.egypt),
                    code = CountryCode.EG,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.france),
                    code = CountryCode.FR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.united_kingdom),
                    code = CountryCode.GB,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.greece),
                    code = CountryCode.GR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hong_kong),
                    code = CountryCode.HK,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hungary),
                    code = CountryCode.HU,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.indonesia),
                    code = CountryCode.ID,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.ireland),
                    code = CountryCode.IE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.israel),
                    code = CountryCode.IL,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.india),
                    code = CountryCode.IN,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.italy),
                    code = CountryCode.IT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.japan),
                    code = CountryCode.JP,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.republic_of_korea),
                    code = CountryCode.KR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.lithuania),
                    code = CountryCode.LT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.latvia),
                    code = CountryCode.LV,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.morocco),
                    code = CountryCode.MA,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.mexico),
                    code = CountryCode.MX,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.malaysia),
                    code = CountryCode.MY,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.nigeria),
                    code = CountryCode.NG,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.netherlands),
                    code = CountryCode.NL,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.norway),
                    code = CountryCode.NO,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.new_zealand),
                    code = CountryCode.NZ,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.philippines),
                    code = CountryCode.PH,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.poland),
                    code = CountryCode.PL,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.portugal),
                    code = CountryCode.PT,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.romania),
                    code = CountryCode.RO,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.serbia),
                    code = CountryCode.RS,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.russian_federation),
                    code = CountryCode.RU,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.saudi_arabia),
                    code = CountryCode.SA,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.sweden),
                    code = CountryCode.SE,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.singapore),
                    code = CountryCode.SG,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.slovenia),
                    code = CountryCode.SI,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.slovakia),
                    code = CountryCode.SK,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.thailand),
                    code = CountryCode.TH,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.turkey),
                    code = CountryCode.TR,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.taiwan),
                    code = CountryCode.TW,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.ukraine),
                    code = CountryCode.UA,
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