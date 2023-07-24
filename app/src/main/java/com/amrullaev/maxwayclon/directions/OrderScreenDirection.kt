package com.amrullaev.maxwayclon.directions

import com.amrullaev.maxwayclon.data.models.OrderData


interface OrderScreenDirection {

   suspend fun navigateToActiveOrderDetails(orderData: OrderData)

   suspend fun navigateToHistoryOrderDetails(orderData: OrderData)

}