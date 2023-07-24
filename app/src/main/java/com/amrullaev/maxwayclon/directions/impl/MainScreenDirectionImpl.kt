package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.MainScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenDirection {
    override suspend fun navigateSearchScreen() =
        navigator.navigateTo(MainScreenDirections.actionMainScreenToSearchProductsScreen())

    override suspend fun navigateBasketScreen() =
        navigator.navigateTo(MainScreenDirections.actionMainScreenToBasketScreen())

    override suspend fun navigateProductsDetailsScreen(productWithCount: ProductWithCount) =
        navigator.navigateTo(
            MainScreenDirections.actionMainScreenToProductDetailsScreen(
                productWithCount
            )
        )
}