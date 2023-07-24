package com.amrullaev.maxwayclon.viewModels

import com.amrullaev.maxwayclon.data.models.Address
import kotlinx.coroutines.flow.SharedFlow

interface BranchDetailsViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val address: SharedFlow<String>

    fun getBranchLocation(address: Address)
}