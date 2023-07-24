package com.amrullaev.maxwayclon.domain.impl

import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import kotlinx.coroutines.flow.Flow


interface OrderUseCase {

    fun getOrdersHistory(): Flow<ResultData<List<OrderData>>>

    fun getActiveOrders(): Flow<ResultData<List<OrderData>>>

    fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>>

}