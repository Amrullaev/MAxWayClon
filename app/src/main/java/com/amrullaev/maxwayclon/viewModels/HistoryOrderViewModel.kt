package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.OrderData
import kotlinx.coroutines.flow.SharedFlow

interface HistoryOrderViewModel {

    val allHistoryOrders: SharedFlow<List<OrderData>>

    val loadingSharedFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    fun getAllOrders()

    fun navigateToOrderDetails(orderData: OrderData)

}