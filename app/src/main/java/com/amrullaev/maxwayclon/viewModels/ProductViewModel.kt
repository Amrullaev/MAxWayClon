package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.ProductWithCount
import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/13/2022
interface ProductViewModel {

    val productFlow: SharedFlow<ProductWithCount>

    fun setProduct(productWithCount: ProductWithCount)

    fun openBasketScreen()

    fun productBasketClick()

}