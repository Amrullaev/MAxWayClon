package com.amrullaev.maxwayclon.data.models

import android.os.Parcelable
import com.amrullaev.maxwayclon.data.models.Address
import kotlinx.parcelize.Parcelize


@Parcelize
data class BranchData(
    val id: Long,
    val way: String,
    val address: Address,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val schedule: String
):Parcelable
