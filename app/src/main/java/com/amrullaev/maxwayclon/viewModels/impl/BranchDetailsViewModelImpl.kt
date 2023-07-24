package com.amrullaev.maxwayclon.viewModels.impl

import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.Address
import com.amrullaev.maxwayclon.utils.exceptions.NoInternetConnection
import com.amrullaev.maxwayclon.utils.extensions.hasConnection
import com.amrullaev.maxwayclon.viewModels.BranchDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

@HiltViewModel
class BranchDetailsViewModelImpl @Inject constructor(
    private val geocoder: Geocoder
) : BranchDetailsViewModel, ViewModel() {


    override val address = MutableSharedFlow<String>()

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun getBranchLocation(address: Address) {
        val latLng = address.toLatLong()
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            try {
                if (hasConnection()) {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    this@BranchDetailsViewModelImpl.address.emit(withContext(Dispatchers.IO) {
                        loadingFlow.emit(false)
                        try {
                            if (addresses.size > 0) addresses[0].getAddressLine(0)
                            else "Не указан"
                        } catch (e: Exception) {
                            e.localizedMessage
                        }
                    }!!)
                } else throw NoInternetConnection()
            } catch (e: Exception) {
                loadingFlow.emit(false)
                errorFlow.emit(e.localizedMessage!!)
            }
        }
    }
}