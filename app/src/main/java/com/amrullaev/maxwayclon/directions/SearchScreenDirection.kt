package com.amrullaev.maxwayclon.directions

import com.amrullaev.maxwayclon.data.models.ProductWithCount


interface SearchScreenDirection {

    suspend fun navigateToProductDetails(productWithCount: ProductWithCount)

}