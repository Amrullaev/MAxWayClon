package com.amrullaev.maxwayclon.viewModels.impl

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrullaev.maxwayclon.data.models.CategoryData
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.directions.MainScreenDirection
import com.amrullaev.maxwayclon.domain.impl.MainUseCase
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.utils.extensions.getMessage
import com.amrullaev.maxwayclon.viewModels.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val direction: MainScreenDirection
) : HomeViewModel, ViewModel() {

    override val categoriesFlow = MutableSharedFlow<List<CategoryData>>()

    override val productFlow = MutableLiveData<List<ProductWithCount>>()

    override val loadingSharedFlow = MediatorLiveData<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()


    override fun getAllCategories() {
        mainUseCase.getAllCategories()
            .onEach {
                it.onSuccess { list ->
                    categoriesFlow.emit(list.map { category -> category.toCategoryData()})
                }
            }
            .onEach {
                it.onError { error ->
                    errorMessageFlow.emit(error.getMessage())
                }
            }
            .onEach {
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
            }
            .launchIn(viewModelScope)
    }


    override fun categoryItemClick(categoryData: CategoryData,selectedPos:Int) {
        if (selectedPos==-1){
            Basket.productsListLiveData.value = Basket.productsList
        }else{
            Basket.productsListLiveData.value = Basket.productsList.filter {
                it.productData.categoryId ==categoryData.id
            }
        }
    }

    override fun getAllProducts() {
        viewModelScope.launch {
            loadingSharedFlow.value = true
            mainUseCase.getAllProducts().collectLatest {
                it.onSuccess { list ->
                    Basket.setList(list.map { productDto ->  productDto.toProductData()})
                }
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                it.onError { error ->
                    errorMessageFlow.emit(error.getMessage())
                }
                loadingSharedFlow.value = false
            }
        }
    }

    override fun addBasket(productWithCount: ProductWithCount) {
        Basket.addProduct(productWithCount)
    }

    override fun navigateBasketScreen() {
        viewModelScope.launch {
            direction.navigateBasketScreen()
        }
    }

    override fun openProductDetailsScreen(productWithCount: ProductWithCount) {
        viewModelScope.launch {
            direction.navigateProductsDetailsScreen(productWithCount)
        }
    }

    override fun searchClicked() {
        viewModelScope.launch {
            direction.navigateSearchScreen()
        }
    }
}
