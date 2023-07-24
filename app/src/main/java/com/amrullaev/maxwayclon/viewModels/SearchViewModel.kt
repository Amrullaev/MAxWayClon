package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.ProductWithCount
import kotlinx.coroutines.flow.SharedFlow

interface SearchViewModel {

    val productSharedFlow: SharedFlow<List<ProductWithCount>>

    fun navigateToProductDetails(productWithCount: ProductWithCount)

    fun search(query: String)

}