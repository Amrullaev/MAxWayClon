package com.amrullaev.maxwayclon.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentHomeBinding
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.utils.extensions.hideProgress
import com.amrullaev.maxwayclon.utils.extensions.showMessageDialog
import com.amrullaev.maxwayclon.utils.extensions.showProgress
import com.amrullaev.maxwayclon.viewModels.HomeViewModel
import com.amrullaev.maxwayclon.viewModels.impl.HomeViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    private val viewBinding: FragmentHomeBinding by viewBinding()

    private val categoryAdapter: CategoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoryAdapter()
    }

    private val productsAdapter: ProductsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ProductsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listCategories.adapter = categoryAdapter

        viewBinding.listProducts.adapter = productsAdapter

        viewModel.loadingSharedFlow.observe(viewLifecycleOwner) {
            if (it) showProgress() else hideProgress()
        }

        viewModel.messageFlow.onEach {
            showMessageDialog(message = it)
        }.launchIn(lifecycleScope)

        viewBinding.tvSearch.setOnClickListener {
            viewModel.searchClicked()
        }
        productsAdapter.setItemBasketClickListener {
            viewModel.addBasket(it)
        }

        productsAdapter.setOpenBasketClickListener {
            viewModel.navigateBasketScreen()
        }
        productsAdapter.setOpenProductDetailsListener {
            viewModel.openProductDetailsScreen(it)
        }
        categoryAdapter.setCategoryListener {
            viewModel.categoryItemClick(it, categoryAdapter.selectedPos)
        }

        viewModel.categoriesFlow.onEach {
            categoryAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        Basket.productsListLiveData.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }

        viewModel.getAllCategories()
        viewModel.getAllProducts()

    }


}