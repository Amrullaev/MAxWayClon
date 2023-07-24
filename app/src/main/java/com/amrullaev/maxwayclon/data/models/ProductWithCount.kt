package com.amrullaev.maxwayclon.data.models

import android.os.Parcelable
import com.amrullaev.maxwayclon.data.models.dto.OrderItemDto
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductWithCount(
    val productData: ProductData,
    var count: Int = 0
) : Parcelable {
    fun toProductOrder() = ProductOrder(productId = productData.id, count)

    fun toOrderItem() = OrderItemDto(productData.id, count)
}
