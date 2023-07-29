import com.amrullaev.maxwayclon.R

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.databinding.PageHistoryOrdersBinding
import com.amrullaev.maxwayclon.ui.orders.history.HistoryOrderAdapter
import com.amrullaev.maxwayclon.utils.extensions.hideProgress
import com.amrullaev.maxwayclon.utils.extensions.showErrorDialog
import com.amrullaev.maxwayclon.utils.extensions.showMessageDialog
import com.amrullaev.maxwayclon.utils.extensions.showProgress
import com.amrullaev.maxwayclon.viewModels.HistoryOrderViewModel
import com.amrullaev.maxwayclon.viewModels.impl.HistoryOrderViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach



@AndroidEntryPoint
class HistoryOrdersFragment : Fragment(R.layout.page_history_orders) {

    private val viewModel: HistoryOrderViewModel by viewModels<HistoryOrderViewModelImpl>()

    private val viewBinding: PageHistoryOrdersBinding by viewBinding()

    private val adapter: HistoryOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HistoryOrderAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listHistoryOrders.adapter = adapter
        viewModel.allHistoryOrders.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToOrderDetails(it)
        }

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorMessageFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)
    }
}