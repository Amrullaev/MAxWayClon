package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.directions.ProductDetailsDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class ProductDetailsDirectionImpl @Inject constructor(private val navigator: Navigator) :
    ProductDetailsDirection {
    override suspend fun navigateToBasket() {
        navigator.navigateTo(ProductDetailsScreenDirections.actionProductDetailsScreenToBasketScreen())
    }
}