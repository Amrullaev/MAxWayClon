package com.amrullaev.maxwayclon.data.models

import com.amrullaev.maxwayclon.data.models.enums.OrderStatus
import com.amrullaev.maxwayclon.data.models.enums.OrderType

data class OrderData(
    val id: Long,
    var createdDate: String,
    val products: List<ProductItem>,
    val allPrice: Double,
    val orderType: OrderType,
    val status: OrderStatus,
    val comment: String = "",
    var number: Long
)
