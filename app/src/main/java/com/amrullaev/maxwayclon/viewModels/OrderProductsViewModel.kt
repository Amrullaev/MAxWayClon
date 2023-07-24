package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface OrderProductsViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val successFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val isDeliveryFlow: StateFlow<Boolean>

    val deliveryAddress: StateFlow<String>

    fun orderConfirmClick(orderDto: OrderDto)

    fun setDeliveryMethod(isDelivery: Boolean)

    fun navigateToMain()

    fun navigateToMap()
}