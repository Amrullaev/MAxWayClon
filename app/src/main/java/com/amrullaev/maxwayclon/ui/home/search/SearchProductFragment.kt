package com.amrullaev.maxwayclon.ui.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentSearchProductBinding
import com.amrullaev.maxwayclon.viewModels.SearchViewModel
import com.amrullaev.maxwayclon.viewModels.impl.SearchViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class SearchProductFragment : Fragment(R.layout.fragment_search_product) {

    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()

    private val viewBinding: FragmentSearchProductBinding by viewBinding()

    private val adapter: SearchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listSearchProducts.adapter = adapter

        viewModel.productSharedFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.inputProduct.textChanges().debounce(200L).onEach {
            viewModel.search(it.toString())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToProductDetails(it)
        }

        viewBinding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }


    }


}