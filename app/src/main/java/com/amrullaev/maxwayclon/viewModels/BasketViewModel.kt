package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.ProductWithCount
import kotlinx.coroutines.flow.SharedFlow

interface BasketViewModel {

    val loadingSharedFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    val openConfirmDialog:SharedFlow<Unit>

    fun removeProduct(productWithCount: ProductWithCount)

    fun deleteProduct(productWithCount: ProductWithCount)

    fun addProduct(productWithCount: ProductWithCount)

    fun confirmClicked()

    fun navigateOrderCheckoutScreen()
}