package dev.jokku.aggregate.data.remote

object HttpRouts {
    private const val BASE_URL = "https://newsapi.org/v2"
    const val API_KEY_VALUE = "064c28c388924a0faf1cd366a9b85e0d"

    /**
     * Search through millions of articles from over 80,000 large and small news sources and blogs.
     *
     * This endpoint suits article discovery and analysis.
     */
    const val EVERYTHING = "$BASE_URL/everything"

    /**
     * This endpoint provides live top and breaking headlines for a country, specific category in a
     * country, single source, or multiple sources. You can also search with keywords. Articles are
     * sorted by the earliest date published first.
     *
     * This endpoint is great for retrieving headlines for use with news tickers or similar.
     */
    const val TOP_HEADLINES = "$BASE_URL/top-headlines"

    /**
     * This endpoint returns the subset of news publishers that top headlines (/v2/top-headlines)
     * are available from. It's mainly a convenience endpoint that you can use to keep track of the
     * publishers available on the API, and you can pipe it straight through to your users.
     */
    const val TOP_HEADLINES_SOURCES = "$TOP_HEADLINES/sources"

    /**
     * Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     */
    const val API_KEY = "apiKey"

    /**
     * Keywords or phrases to search for in the article title and body.
     *
     * Advanced search is supported here:
     *
     * - Surround phrases with quotes (") for exact match.
     * - Prepend words or phrases that must appear with a + symbol. Eg: +bitcoin
     * - Prepend words that must not appear with a - symbol. Eg: -bitcoin
     * - Alternatively you can use the AND / OR / NOT keywords, and optionally group these with
     * parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
     *
     * The complete value for q must be URL-encoded.
     *
     * Max length: 500 chars.
     */
    const val QUERY = "q"

    /**
     * The fields to restrict your q search to.
     *
     * The possible options are:
     *
     * - title
     * - description
     * - content
     *
     * Multiple options can be specified by separating them with a comma, for example:
     * title,content.
     *
     * This parameter is useful if you have an edge case where searching all the fields is not
     * giving the desired outcome, but generally you should not need to set this.
     *
     * Default: all fields are searched.
     */
    const val SEARCH_IN = "searchIn"

    /**
     * The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options:
     * ae, ar, at, au, be, bg, br, ca, ch, cn, co, cu, cz, de, eg, fr, gb, gr, hk, hu, id, ie, il,
     * in, it, jp, kr, lt, lv, ma, mx, my, ng, nl, no, nz, ph, pl, pt, ro, rs, ru, sa, se, sg, si,
     * sk, th, tr, tw, ua, us, ve, za.
     *
     * Note: you can't mix this param with the sources param.
     */
    const val COUNTRY = "country"

    /**
     * The category you want to get headlines for. Possible options:
     * business, entertainment, general, health, science, sports, technology.
     *
     * Note: you can't mix this param with the sources param.
     */
    const val CATEGORY = "category"

    /**
     * A comma-separated string of identifiers (maximum 20) for the news sources or blogs you want
     * headlines from. Use the /sources endpoint to locate these programmatically or look at the
     * sources index.
     */
    const val SOURCES = "sources"

    /**
     * A comma-separated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com)
     * to restrict the search to.
     */
    const val DOMAINS = "domains"

    /**
     * A comma-separated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com)
     * to remove from the results.
     */
    const val EXCLUDE_DOMAINS = "excludeDomains"

    /**
     * A date and optional time for the oldest article allowed. This should be in ISO 8601 format
     * (e.g. 2023-05-31 or 2023-05-31T19:32:33)
     *
     * Default: the oldest according to your plan.
     */
    const val FROM = "from"

    /**
     * A date and optional time for the newest article allowed. This should be in ISO 8601 format
     * (e.g. 2023-05-31 or 2023-05-31T19:32:33)
     *
     * Default: the newest according to your plan.
     */
    const val TO = "to"

    /**
     * The 2-letter ISO-639-1 code of the language you want to get headlines for.
     * Possible options: ar, de, en, es, fr, he, it, nl, no, pt, ru, sv, ud, zh.
     *
     * Default: all languages returned.
     */
    const val LANGUAGE = "language"

    /**
     * The order to sort the articles in. Possible options: relevancy, popularity, publishedAt.
     * relevancy = articles more closely related to q come first.
     * popularity = articles from popular sources and publishers come first.
     * publishedAt = newest articles come first.
     *
     * Default: publishedAt
     */
    const val SORT_BY = "sortBy"

    /**
     * The number of results to return per page.
     *
     * Default: 100. Maximum: 100.
     */
    const val PAGE_SIZE = "pageSize"

    /**
     * Use this to page through the results.
     *
     * Default: 1.
     */
    const val PAGE = "page"
}