package dev.aggregate.favorites

import dev.aggregate.model.ui.UiCategorisedArticles

sealed class FavoritesState(open val stateData: UiCategorisedArticles?) {
    class Loading(stateData: UiCategorisedArticles? = null) : FavoritesState(stateData)
    class Failure(stateData: UiCategorisedArticles? = null) : FavoritesState(stateData)
    class Success(override val stateData: UiCategorisedArticles) : FavoritesState(stateData)
}
