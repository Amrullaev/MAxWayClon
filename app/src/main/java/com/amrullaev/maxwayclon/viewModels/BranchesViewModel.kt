package com.amrullaev.maxwayclon.viewModels

import androidx.lifecycle.LiveData
import com.amrullaev.maxwayclon.data.models.BranchData
import kotlinx.coroutines.flow.SharedFlow

interface BranchesViewModel {

    val loadingSharedFlow: LiveData<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    val branchesFlow: SharedFlow<List<BranchData>>

    fun navigateToBranchDetails(branchData: BranchData)

}