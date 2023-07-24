package com.amrullaev.maxwayclon.directions.impl

import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.directions.OrderScreenDirection
import com.amrullaev.maxwayclon.navigation.Navigator
import javax.inject.Inject

class OrderScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : OrderScreenDirection {

    override suspend fun navigateToActiveOrderDetails(orderData: OrderData) {

    }

    override suspend fun navigateToHistoryOrderDetails(orderData: OrderData) {
      //Todo  navigator.navigateTo()
    }
}