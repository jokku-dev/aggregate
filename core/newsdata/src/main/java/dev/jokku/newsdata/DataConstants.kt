package dev.jokku.newsdata

enum class ResponseStatus(val value: String) {
    OK("ok"),
    ERROR("error")
}

sealed interface UrlParameter

enum class LanguageCode(val value: String) : UrlParameter {
    AR("ar"), DE("de"), EN("en"), ES("es"), FR("fr"),
    HE("he"), IT("it"), NL("nl"), NO("no"), PT("pt"),
    RU("ru"), SV("sv"), ZH("zh")
}

enum class CountryCode(val value: String) : UrlParameter {
    AE("ae"), AR("ar"), AT("at"), AU("au"), BE("be"),
    BG("bg"), BR("br"), CA("ca"), CH("ch"), CN("cn"),
    CO("co"), CU("cu"), CZ("cz"), DE("de"), EG("eg"),
    FR("fr"), GB("gb"), GR("gr"), HK("hk"), HU("hu"),
    ID("id"), IE("ie"), IL("il"), IN("in"), IT("it"),
    JP("jp"), KR("kr"), LT("lt"), LV("lv"), MA("ma"),
    MX("mx"), MY("my"), NG("ng"), NL("nl"), NO("no"),
    NZ("nz"), PH("ph"), PL("pl"), PT("pt"), RO("ro"),
    RS("rs"), RU("ru"), SA("sa"), SE("se"), SG("sg"),
    SI("si"), SK("sk"), TH("th"), TR("tr"), TW("tw"),
    UA("ua"), US("us"), VE("ve"), ZA("za")
}

enum class CategoryCode(val value: String) : UrlParameter {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}

enum class SearchIn(val value: String) : UrlParameter {
    TITLE("title"),
    DESCRIPTION("description"),
    CONTENT("content")
}

enum class SortBy(val value: String) : UrlParameter {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("publishedAt")
}