package com.amrullaev.maxwayclon.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val desc: String,
    val categoryId: Long
) : Parcelable
