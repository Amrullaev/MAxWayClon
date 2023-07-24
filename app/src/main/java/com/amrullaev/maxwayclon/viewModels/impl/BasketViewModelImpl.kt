package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.BasketScreenDirection
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.viewModels.BasketViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class BasketViewModelImpl @Inject constructor(
    private val basketScreenDirections: BasketScreenDirection
) : BasketViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override val openConfirmDialog = MutableSharedFlow<Unit>()

    override fun removeProduct(productWithCount: ProductWithCount) {
        Basket.removeProduct(productWithCount)
    }

    override fun deleteProduct(productWithCount: ProductWithCount) {
        Basket.removeProduct(productWithCount.copy(count = 1))
    }

    override fun addProduct(productWithCount: ProductWithCount) {
        Basket.addProduct(productWithCount)
    }

    override fun confirmClicked() {
        viewModelScope.launch {
            openConfirmDialog.emit(Unit)
        }
    }

    override fun navigateOrderCheckoutScreen() {
        viewModelScope.launch {
            basketScreenDirections.navigateCheckoutScreen()
        }
    }

}