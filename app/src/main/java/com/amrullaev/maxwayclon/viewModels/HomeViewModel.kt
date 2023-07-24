package com.amrullaev.maxwayclon.viewModels

import androidx.lifecycle.LiveData
import com.amrullaev.maxwayclon.data.models.CategoryData
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import kotlinx.coroutines.flow.SharedFlow

interface HomeViewModel {

    val categoriesFlow: SharedFlow<List<CategoryData>>

    val productFlow:LiveData<List<ProductWithCount>>

    val loadingSharedFlow: LiveData<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    fun getAllCategories()

    fun categoryItemClick(categoryData: CategoryData,selectedPos:Int)

    fun getAllProducts()

    fun addBasket(productWithCount: ProductWithCount)

    fun navigateBasketScreen()

    fun openProductDetailsScreen(productWithCount: ProductWithCount)

    fun searchClicked()

}