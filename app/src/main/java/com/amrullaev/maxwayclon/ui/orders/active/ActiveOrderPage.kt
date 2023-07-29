package com.amrullaev.maxwayclon.ui.orders.active

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.PageActiveOrdersBinding
import com.amrullaev.maxwayclon.utils.extensions.hideProgress
import com.amrullaev.maxwayclon.utils.extensions.showErrorDialog
import com.amrullaev.maxwayclon.utils.extensions.showMessageDialog
import com.amrullaev.maxwayclon.utils.extensions.showProgress
import com.amrullaev.maxwayclon.viewModels.ActiveOrderViewModel
import com.amrullaev.maxwayclon.viewModels.impl.ActiveOrderViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ActiveOrdersPage : Fragment(R.layout.page_active_orders) {

    private val viewModel: ActiveOrderViewModel by viewModels<ActiveOrderViewModelImpl>()

    private val viewBinding: PageActiveOrdersBinding by viewBinding()

    private val adapter: ActiveOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ActiveOrderAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listActiveOrders.adapter = adapter

        viewModel.allActiveOrders.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorMessageFlow.onEach {
            showErrorDialog(message = it)
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllOrders()
    }

}