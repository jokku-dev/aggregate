package com.jokku.aggregate.presentation.screens.welcome

import com.jokku.aggregate.R
import com.jokku.aggregate.data.UrlParameterValue
import com.jokku.aggregate.presentation.model.UiOnBoardingPage
import com.jokku.aggregate.presentation.model.UiSelectableCategory
import com.jokku.aggregate.presentation.model.UiText

interface WelcomeStateProvider {
    fun createFavoriteCategoriesState(): FavoriteCategoriesState
    fun createOnBoardingState(): OnBoardingState
}

class WelcomeStateProviderFactory : WelcomeStateProvider {

    override fun createFavoriteCategoriesState(): FavoriteCategoriesState {
        val defaultLanguages = listOf(
            UiSelectableCategory(
                name = UiText.StringResource(R.string.arabic),
                code = UrlParameterValue.LanguageCode.AR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.german),
                code = UrlParameterValue.LanguageCode.DE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.english),
                code = UrlParameterValue.LanguageCode.EN,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.spanish),
                code = UrlParameterValue.LanguageCode.ES,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.french),
                code = UrlParameterValue.LanguageCode.FR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.hebrew),
                code = UrlParameterValue.LanguageCode.HE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.italian),
                code = UrlParameterValue.LanguageCode.IT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.dutch),
                code = UrlParameterValue.LanguageCode.NL,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.norwegian),
                code = UrlParameterValue.LanguageCode.NO,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.portuguese),
                code = UrlParameterValue.LanguageCode.PT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.russian),
                code = UrlParameterValue.LanguageCode.RU,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.swedish),
                code = UrlParameterValue.LanguageCode.SV,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.chinese),
                code = UrlParameterValue.LanguageCode.ZH,
                selected = false
            ),
        )

        val defaultCategories = listOf(
            UiSelectableCategory(
                name = UiText.StringResource(R.string.business),
                code = UrlParameterValue.Category.BUSINESS,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.entertainment),
                code = UrlParameterValue.Category.ENTERTAINMENT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.general),
                code = UrlParameterValue.Category.GENERAL,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.health),
                code = UrlParameterValue.Category.HEALTH,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.science),
                code = UrlParameterValue.Category.SCIENCE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.sports),
                code = UrlParameterValue.Category.SPORTS,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.technology),
                code = UrlParameterValue.Category.TECHNOLOGY,
                selected = false
            ),
        )

        val defaultCountries = listOf(
            UiSelectableCategory(
                name = UiText.StringResource(R.string.united_arab_emirates),
                code = UrlParameterValue.CountryCode.AE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.argentina),
                code = UrlParameterValue.CountryCode.AR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.austria),
                code = UrlParameterValue.CountryCode.AT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.australia),
                code = UrlParameterValue.CountryCode.AU,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.belgium),
                code = UrlParameterValue.CountryCode.BE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.bulgaria),
                code = UrlParameterValue.CountryCode.BG,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.brazil),
                code = UrlParameterValue.CountryCode.BR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.canada),
                code = UrlParameterValue.CountryCode.CA,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.switzerland),
                code = UrlParameterValue.CountryCode.CH,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.china),
                code = UrlParameterValue.CountryCode.CN,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.colombia),
                code = UrlParameterValue.CountryCode.CO,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.cuba),
                code = UrlParameterValue.CountryCode.CU,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.czechia),
                code = UrlParameterValue.CountryCode.CZ,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.germany),
                code = UrlParameterValue.CountryCode.DE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.egypt),
                code = UrlParameterValue.CountryCode.EG,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.france),
                code = UrlParameterValue.CountryCode.FR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.united_kingdom),
                code = UrlParameterValue.CountryCode.GB,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.greece),
                code = UrlParameterValue.CountryCode.GR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.hong_kong),
                code = UrlParameterValue.CountryCode.HK,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.hungary),
                code = UrlParameterValue.CountryCode.HU,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.indonesia),
                code = UrlParameterValue.CountryCode.ID,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.ireland),
                code = UrlParameterValue.CountryCode.IE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.israel),
                code = UrlParameterValue.CountryCode.IL,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.india),
                code = UrlParameterValue.CountryCode.IN,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.italy),
                code = UrlParameterValue.CountryCode.IT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.japan),
                code = UrlParameterValue.CountryCode.JP,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.republic_of_korea),
                code = UrlParameterValue.CountryCode.KR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.lithuania),
                code = UrlParameterValue.CountryCode.LT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.latvia),
                code = UrlParameterValue.CountryCode.LV,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.morocco),
                code = UrlParameterValue.CountryCode.MA,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.mexico),
                code = UrlParameterValue.CountryCode.MX,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.malaysia),
                code = UrlParameterValue.CountryCode.MY,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.nigeria),
                code = UrlParameterValue.CountryCode.NG,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.netherlands),
                code = UrlParameterValue.CountryCode.NL,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.norway),
                code = UrlParameterValue.CountryCode.NO,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.new_zealand),
                code = UrlParameterValue.CountryCode.NZ,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.philippines),
                code = UrlParameterValue.CountryCode.PH,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.poland),
                code = UrlParameterValue.CountryCode.PL,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.portugal),
                code = UrlParameterValue.CountryCode.PT,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.romania),
                code = UrlParameterValue.CountryCode.RO,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.serbia),
                code = UrlParameterValue.CountryCode.RS,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.russian_federation),
                code = UrlParameterValue.CountryCode.RU,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.saudi_arabia),
                code = UrlParameterValue.CountryCode.SA,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.sweden),
                code = UrlParameterValue.CountryCode.SE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.singapore),
                code = UrlParameterValue.CountryCode.SG,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.slovenia),
                code = UrlParameterValue.CountryCode.SI,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.slovakia),
                code = UrlParameterValue.CountryCode.SK,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.thailand),
                code = UrlParameterValue.CountryCode.TH,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.turkey),
                code = UrlParameterValue.CountryCode.TR,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.taiwan),
                code = UrlParameterValue.CountryCode.TW,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.ukraine),
                code = UrlParameterValue.CountryCode.UA,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.united_states_of_america),
                code = UrlParameterValue.CountryCode.US,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.venezuela),
                code = UrlParameterValue.CountryCode.VE,
                selected = false
            ),
            UiSelectableCategory(
                name = UiText.StringResource(R.string.south_africa),
                code = UrlParameterValue.CountryCode.ZA,
                selected = false
            )
        )

        return FavoriteCategoriesState(
            languages = defaultLanguages,
            categories = defaultCategories,
            countries = defaultCountries
        )
    }

    override fun createOnBoardingState(): OnBoardingState {
        val onBoardingPages = listOf(
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
        return OnBoardingState(
            pages = onBoardingPages
        )
    }
}