package com.amrullaev.maxwayclon.viewModels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow

interface MainViewModel {

    val basketFlow:LiveData<Double>

    val loadingLiveData:LiveData<Boolean>

    fun navigateBasket()

}