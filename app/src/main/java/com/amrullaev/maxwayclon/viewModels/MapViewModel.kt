package com.amrullaev.maxwayclon.viewModels

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.SharedFlow

interface MapViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val toastFlow: SharedFlow<String>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val address:SharedFlow<String>

    val currentLocationFlow:LiveData<LatLng>

    fun getAddressByLocation(latLng: LatLng)

    fun requestCurrentLocation()
}