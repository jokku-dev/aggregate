package dev.jokku.welcome

import dev.jokku.aggregate.R
import dev.jokku.newsdata.CountryCode
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
    fun provideCategoryNameByCode(type: CategoryType, code: dev.jokku.newsdata.UrlParameter): dev.jokku.aggregate.presentation.model.UiText
}

class LocalDataProviderFactory : LocalDataProvider {

    override fun provideCategoryNameByCode(type: CategoryType, code: dev.jokku.newsdata.UrlParameter): dev.jokku.aggregate.presentation.model.UiText {
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
                    code = dev.jokku.newsdata.LanguageCode.AR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.german),
                    code = dev.jokku.newsdata.LanguageCode.DE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.english),
                    code = dev.jokku.newsdata.LanguageCode.EN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.spanish),
                    code = dev.jokku.newsdata.LanguageCode.ES,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.french),
                    code = dev.jokku.newsdata.LanguageCode.FR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hebrew),
                    code = dev.jokku.newsdata.LanguageCode.HE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.italian),
                    code = dev.jokku.newsdata.LanguageCode.IT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.dutch),
                    code = dev.jokku.newsdata.LanguageCode.NL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.norwegian),
                    code = dev.jokku.newsdata.LanguageCode.NO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.portuguese),
                    code = dev.jokku.newsdata.LanguageCode.PT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.russian),
                    code = dev.jokku.newsdata.LanguageCode.RU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.swedish),
                    code = dev.jokku.newsdata.LanguageCode.SV,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.chinese),
                    code = dev.jokku.newsdata.LanguageCode.ZH,
                    selected = false
                ),
            )

            CategoryType.CATEGORY -> listOf(
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.business),
                    code = dev.jokku.newsdata.CategoryCode.BUSINESS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.entertainment),
                    code = dev.jokku.newsdata.CategoryCode.ENTERTAINMENT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.general),
                    code = dev.jokku.newsdata.CategoryCode.GENERAL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.health),
                    code = dev.jokku.newsdata.CategoryCode.HEALTH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.science),
                    code = dev.jokku.newsdata.CategoryCode.SCIENCE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.sports),
                    code = dev.jokku.newsdata.CategoryCode.SPORTS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.technology),
                    code = dev.jokku.newsdata.CategoryCode.TECHNOLOGY,
                    selected = false
                ),
            )

            CategoryType.COUNTRY -> listOf(
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.united_arab_emirates),
                    code = dev.jokku.newsdata.CountryCode.AE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.argentina),
                    code = dev.jokku.newsdata.CountryCode.AR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.austria),
                    code = dev.jokku.newsdata.CountryCode.AT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.australia),
                    code = dev.jokku.newsdata.CountryCode.AU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.belgium),
                    code = dev.jokku.newsdata.CountryCode.BE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.bulgaria),
                    code = dev.jokku.newsdata.CountryCode.BG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.brazil),
                    code = dev.jokku.newsdata.CountryCode.BR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.canada),
                    code = dev.jokku.newsdata.CountryCode.CA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.switzerland),
                    code = dev.jokku.newsdata.CountryCode.CH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.china),
                    code = dev.jokku.newsdata.CountryCode.CN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.colombia),
                    code = dev.jokku.newsdata.CountryCode.CO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.cuba),
                    code = dev.jokku.newsdata.CountryCode.CU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.czechia),
                    code = dev.jokku.newsdata.CountryCode.CZ,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.germany),
                    code = dev.jokku.newsdata.CountryCode.DE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.egypt),
                    code = dev.jokku.newsdata.CountryCode.EG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.france),
                    code = dev.jokku.newsdata.CountryCode.FR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.united_kingdom),
                    code = dev.jokku.newsdata.CountryCode.GB,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.greece),
                    code = dev.jokku.newsdata.CountryCode.GR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hong_kong),
                    code = dev.jokku.newsdata.CountryCode.HK,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.hungary),
                    code = dev.jokku.newsdata.CountryCode.HU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.indonesia),
                    code = dev.jokku.newsdata.CountryCode.ID,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.ireland),
                    code = dev.jokku.newsdata.CountryCode.IE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.israel),
                    code = dev.jokku.newsdata.CountryCode.IL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.india),
                    code = dev.jokku.newsdata.CountryCode.IN,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.italy),
                    code = dev.jokku.newsdata.CountryCode.IT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.japan),
                    code = dev.jokku.newsdata.CountryCode.JP,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.republic_of_korea),
                    code = dev.jokku.newsdata.CountryCode.KR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.lithuania),
                    code = dev.jokku.newsdata.CountryCode.LT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.latvia),
                    code = dev.jokku.newsdata.CountryCode.LV,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.morocco),
                    code = dev.jokku.newsdata.CountryCode.MA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.mexico),
                    code = dev.jokku.newsdata.CountryCode.MX,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.malaysia),
                    code = dev.jokku.newsdata.CountryCode.MY,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.nigeria),
                    code = dev.jokku.newsdata.CountryCode.NG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.netherlands),
                    code = dev.jokku.newsdata.CountryCode.NL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.norway),
                    code = dev.jokku.newsdata.CountryCode.NO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.new_zealand),
                    code = dev.jokku.newsdata.CountryCode.NZ,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.philippines),
                    code = dev.jokku.newsdata.CountryCode.PH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.poland),
                    code = dev.jokku.newsdata.CountryCode.PL,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.portugal),
                    code = dev.jokku.newsdata.CountryCode.PT,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.romania),
                    code = dev.jokku.newsdata.CountryCode.RO,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.serbia),
                    code = dev.jokku.newsdata.CountryCode.RS,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.russian_federation),
                    code = dev.jokku.newsdata.CountryCode.RU,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.saudi_arabia),
                    code = dev.jokku.newsdata.CountryCode.SA,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.sweden),
                    code = dev.jokku.newsdata.CountryCode.SE,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.singapore),
                    code = dev.jokku.newsdata.CountryCode.SG,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.slovenia),
                    code = dev.jokku.newsdata.CountryCode.SI,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.slovakia),
                    code = dev.jokku.newsdata.CountryCode.SK,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.thailand),
                    code = dev.jokku.newsdata.CountryCode.TH,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.turkey),
                    code = dev.jokku.newsdata.CountryCode.TR,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.taiwan),
                    code = dev.jokku.newsdata.CountryCode.TW,
                    selected = false
                ),
                dev.jokku.aggregate.presentation.model.UiCategory(
                    name = dev.jokku.aggregate.presentation.model.UiText.StringResource(dev.jokku.aggregate.R.string.ukraine),
                    code = dev.jokku.newsdata.CountryCode.UA,
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