package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.BranchData
import com.amrullaev.maxwayclon.directions.BranchesDirection
import com.amrullaev.maxwayclon.domain.impl.BranchesUseCase
import com.amrullaev.maxwayclon.viewModels.BranchesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class BranchesViewModelImpl @Inject constructor(
    private val useCaseImpl: BranchesUseCase,
    private val direction: BranchesDirection
) : BranchesViewModel, ViewModel() {

    override val loadingSharedFlow = MutableLiveData<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override val branchesFlow = MutableSharedFlow<List<BranchData>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadingSharedFlow.postValue(true)
            useCaseImpl.getAllBranches().collectLatest {
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                it.onSuccess { list ->
                    branchesFlow.emit(list)
                }
                it.onError { error ->
                    errorMessageFlow.emit(error.message ?: "Unknown exception")
                }
                loadingSharedFlow.postValue(false)
            }
        }
    }

    override fun navigateToBranchDetails(branchData: BranchData) {
        viewModelScope.launch {
            direction.navigateToBranchesDetails(branchData)
        }
    }
}