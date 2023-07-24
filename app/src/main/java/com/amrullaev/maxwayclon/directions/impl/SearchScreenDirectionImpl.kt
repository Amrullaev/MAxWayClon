package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.SearchScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class SearchScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SearchScreenDirection {
    override suspend fun navigateToProductDetails(productWithCount: ProductWithCount) {
        navigator.navigateTo(SearchProductsScreenDirections.actionSearchProductsScreenToProductDetailsScreen(productWithCount))
    }
}