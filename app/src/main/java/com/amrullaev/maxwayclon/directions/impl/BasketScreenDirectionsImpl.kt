package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.BasketScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject


class BasketScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : BasketScreenDirection {
    override suspend fun navigateCheckoutScreen() {
        navigator.navigateTo(BasketScreenDirections.actionBasketScreenToOrderProductsScreen())
    }
}