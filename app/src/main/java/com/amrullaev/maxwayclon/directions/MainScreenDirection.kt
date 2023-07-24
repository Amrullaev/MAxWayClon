package com.amrullaev.maxwayclon.directions

import com.amrullaev.maxwayclon.data.models.ProductWithCount


interface MainScreenDirection {

    suspend fun navigateSearchScreen()

    suspend fun navigateBasketScreen()

    suspend fun navigateProductsDetailsScreen(productWithCount: ProductWithCount)

}