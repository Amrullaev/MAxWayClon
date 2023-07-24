package com.amrullaev.maxwayclon.data.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val lat: Double,
    val lon: Double
) : Parcelable {
    fun toLatLong() = LatLng(lat, lon)
}
