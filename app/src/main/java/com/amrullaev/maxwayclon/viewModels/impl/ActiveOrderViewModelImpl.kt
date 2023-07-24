package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.domain.impl.OrderUseCase
import com.amrullaev.maxwayclon.utils.extensions.getMessage
import com.amrullaev.maxwayclon.viewModels.ActiveOrderViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ActiveOrderViewModelImpl @Inject constructor(
    private val orderUseCase: OrderUseCase
) : ActiveOrderViewModel, ViewModel() {

    override val allActiveOrders = MutableSharedFlow<List<OrderData>>()

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override fun getAllOrders() {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            orderUseCase.getActiveOrders().collectLatest {
                it.onSuccess { list ->
                    allActiveOrders.emit(list)
                }
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                it.onError { throwable ->
                    errorMessageFlow.emit(throwable.getMessage())
                }
                loadingSharedFlow.emit(false)
            }
        }
    }

    override fun navigateToOrderDetails() {
        //TODO navigate
    }
}