package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.OrderProductDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class OrderProductDirectionImpl @Inject constructor(private val navigator: Navigator) :
    OrderProductDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(OrderProductsScreenDirections.actionOrderProductsScreenToMainScreen())
    }

    override suspend fun navigateToMap() {
        navigator.navigateTo(OrderProductsScreenDirections.actionOrderProductsScreenToOrderMapFragment())
    }
}