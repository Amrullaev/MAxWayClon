package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.directions.OrderScreenDirection
import com.amrullaev.maxwayclon.domain.impl.OrderUseCase
import com.amrullaev.maxwayclon.utils.extensions.getMessage
import com.amrullaev.maxwayclon.viewModels.HistoryOrderViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HistoryOrderViewModelImpl @Inject constructor(
    private val orderUseCase: OrderUseCase,
    private val direction: OrderScreenDirection
) : HistoryOrderViewModel, ViewModel() {

    override val allHistoryOrders = MutableSharedFlow<List<OrderData>>()

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override fun getAllOrders() {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            orderUseCase.getOrdersHistory().collectLatest {
                it.onSuccess { list ->
                    allHistoryOrders.emit(list)
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

    override fun navigateToOrderDetails(orderData: OrderData) {
        viewModelScope.launch {
            direction.navigateToHistoryOrderDetails(orderData)
        }
    }
}