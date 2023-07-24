package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.SearchScreenDirection
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.viewModels.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val direction: SearchScreenDirection
) : SearchViewModel, ViewModel() {

    override val productSharedFlow = MutableSharedFlow<List<ProductWithCount>>()


    override fun navigateToProductDetails(productWithCount: ProductWithCount) {
        viewModelScope.launch {
            direction.navigateToProductDetails(productWithCount)
        }
    }

    override fun search(query: String) {
        viewModelScope.launch {
            val list = Basket.productsList.filter {
                it.productData.name.contains(query)
            }
            productSharedFlow.emit(list)
        }
    }
}