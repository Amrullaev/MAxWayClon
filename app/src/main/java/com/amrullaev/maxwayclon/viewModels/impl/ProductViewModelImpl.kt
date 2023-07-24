package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.ProductDetailsDirection
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.viewModels.ProductViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ProductViewModelImpl @Inject constructor(
   private var productDetailsDirection: ProductDetailsDirection
) : ProductViewModel, ViewModel() {

    override val productFlow = MutableSharedFlow<ProductWithCount>()

    private lateinit var productWithCount: ProductWithCount

    override fun setProduct(productWithCount: ProductWithCount) {
        this.productWithCount = productWithCount
        viewModelScope.launch {
            productFlow.emit(productWithCount)
        }
    }

    override fun openBasketScreen() {
        viewModelScope.launch {
           productDetailsDirection.navigateToBasket()
        }
    }

    override fun productBasketClick() {
        viewModelScope.launch {
        productFlow.emit(productWithCount.copy(count = 1))
        }
        Basket.addProduct(productWithCount)
    }
}