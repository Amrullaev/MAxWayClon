package com.amrullaev.maxwayclon.data.models.dto

import com.amrullaev.maxwayclon.data.models.Address
import com.amrullaev.maxwayclon.data.models.enums.OrderType

data class OrderDto(
    val allOrderValue: HashSet<OrderItemDto>,
    val comment: String,
    val orderType: OrderType,
    val address: Address? = null,
    val userId: Long = 0
)
