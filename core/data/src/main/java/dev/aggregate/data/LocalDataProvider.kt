package dev.aggregate.data

import dev.aggregate.model.network.Category
import dev.aggregate.model.network.Country
import dev.aggregate.model.network.Language
import dev.aggregate.model.ui.UiCategory
import dev.aggregate.model.ui.UiOnBoardingPage
import dev.aggregate.model.ui.UiText
import javax.inject.Inject

enum class CategoryType {
    LANGUAGE, CATEGORY, COUNTRY
}

interface LocalDataProvider {
    fun provideNewsCategories(type: CategoryType): List<UiCategory>
    fun provideOnBoardingPages(): List<UiOnBoardingPage>
    fun provideCategoryNameByCode(
        type: CategoryType,
        code: String
    ): UiText
}

class LocalDataProviderFactory @Inject constructor() : LocalDataProvider {

    override fun provideCategoryNameByCode(
        type: CategoryType,
        code: String
    ): UiText {
        return provideNewsCategories(type)
            .find { uiCategory ->
                uiCategory.code == code
            }?.name ?: UiText.StringResource(R.string.unknown_category)
    }

    override fun provideNewsCategories(type: CategoryType): List<UiCategory> {
        return when (type) {
            CategoryType.LANGUAGE -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.arabic),
                    code = Language.AR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.german),
                    code = Language.DE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.english),
                    code = Language.EN.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.spanish),
                    code = Language.ES.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.french),
                    code = Language.FR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hebrew),
                    code = Language.HE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.italian),
                    code = Language.IT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.dutch),
                    code = Language.NL.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.norwegian),
                    code = Language.NO.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.portuguese),
                    code = Language.PT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.russian),
                    code = Language.RU.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.swedish),
                    code = Language.SV.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.chinese),
                    code = Language.ZH.name,
                    selected = false
                ),
            )

            CategoryType.CATEGORY -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.business),
                    code = Category.BUSINESS.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.entertainment),
                    code = Category.ENTERTAINMENT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.general),
                    code = Category.GENERAL.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.health),
                    code = Category.HEALTH.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.science),
                    code = Category.SCIENCE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.sports),
                    code = Category.SPORTS.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.technology),
                    code = Category.TECHNOLOGY.name,
                    selected = false
                ),
            )

            CategoryType.COUNTRY -> listOf(
                UiCategory(
                    name = UiText.StringResource(R.string.united_arab_emirates),
                    code = Country.AE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.argentina),
                    code = Country.AR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.austria),
                    code = Country.AT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.australia),
                    code = Country.AU.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.belgium),
                    code = Country.BE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.bulgaria),
                    code = Country.BG.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.brazil),
                    code = Country.BR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.canada),
                    code = Country.CA.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.switzerland),
                    code = Country.CH.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.china),
                    code = Country.CN.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.colombia),
                    code = Country.CO.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.cuba),
                    code = Country.CU.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.czechia),
                    code = Country.CZ.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.germany),
                    code = Country.DE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.egypt),
                    code = Country.EG.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.france),
                    code = Country.FR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.united_kingdom),
                    code = Country.GB.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.greece),
                    code = Country.GR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hong_kong),
                    code = Country.HK.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.hungary),
                    code = Country.HU.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.indonesia),
                    code = Country.ID.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.ireland),
                    code = Country.IE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.israel),
                    code = Country.IL.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.india),
                    code = Country.IN.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.italy),
                    code = Country.IT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.japan),
                    code = Country.JP.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.republic_of_korea),
                    code = Country.KR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.lithuania),
                    code = Country.LT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.latvia),
                    code = Country.LV.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.morocco),
                    code = Country.MA.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.mexico),
                    code = Country.MX.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.malaysia),
                    code = Country.MY.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.nigeria),
                    code = Country.NG.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.netherlands),
                    code = Country.NL.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.norway),
                    code = Country.NO.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.new_zealand),
                    code = Country.NZ.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.philippines),
                    code = Country.PH.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.poland),
                    code = Country.PL.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.portugal),
                    code = Country.PT.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.romania),
                    code = Country.RO.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.serbia),
                    code = Country.RS.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.russian_federation),
                    code = Country.RU.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.saudi_arabia),
                    code = Country.SA.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.sweden),
                    code = Country.SE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.singapore),
                    code = Country.SG.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.slovenia),
                    code = Country.SI.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.slovakia),
                    code = Country.SK.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.thailand),
                    code = Country.TH.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.turkey),
                    code = Country.TR.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.taiwan),
                    code = Country.TW.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.ukraine),
                    code = Country.UA.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.united_states_of_america),
                    code = Country.US.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.venezuela),
                    code = Country.VE.name,
                    selected = false
                ),
                UiCategory(
                    name = UiText.StringResource(R.string.south_africa),
                    code = Country.ZA.name,
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
