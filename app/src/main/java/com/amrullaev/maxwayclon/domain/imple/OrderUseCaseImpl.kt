package com.amrullaev.maxwayclon.domain.imple

import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.other.ResultData
import com.amrullaev.maxwayclon.domain.impl.OrderUseCase
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderUseCaseImpl @Inject constructor(
    private val maxWayRepository: MaxWayRepository
) : OrderUseCase {
    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> =
        maxWayRepository.getOrdersHistory()

    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> =
        maxWayRepository.getActiveOrders()

    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> =
        maxWayRepository.orderProducts(orderDto)
}