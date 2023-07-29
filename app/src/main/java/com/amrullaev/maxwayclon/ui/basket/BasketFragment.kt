
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.databinding.FragmentBasketBinding
import com.amrullaev.maxwayclon.ui.basket.BasketAdapter
import com.amrullaev.maxwayclon.ui.dialogs.ConfirmDialog
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.utils.extensions.getFinanceType
import com.amrullaev.maxwayclon.viewModels.BasketViewModel
import com.amrullaev.maxwayclon.viewModels.impl.BasketViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {

    private val viewModel: BasketViewModel by viewModels<BasketViewModelImpl>()

    private lateinit var animator: ValueAnimator

    private var oldSum = 0.0

    private val viewBinding: FragmentBasketBinding by viewBinding()

    private val adapter: BasketAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BasketAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listProducts.adapter = adapter

        viewBinding.listProducts.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter.setItemAddListener {
            viewModel.addProduct(it)
        }
        adapter.setItemDeleteListener {
            viewModel.deleteProduct(it)
        }
        adapter.setItemRemovedListener {
            viewModel.removeProduct(it)
        }
        Basket.productsListLiveData.observe(viewLifecycleOwner) { list ->
            val news = list.filter { it.count > 0 }
            viewBinding.btnConfirmOrder.isEnabled = news.isNotEmpty()
            adapter.submitList(news)
            tvPriceAnimator(news)
        }
        viewBinding.btnConfirmOrder.setOnClickListener {
            viewModel.confirmClicked()
        }
        viewModel.openConfirmDialog.onEach {
            val confirmDialog = ConfirmDialog(requireContext())
            confirmDialog.setConfirmClickListener {
                viewModel.navigateOrderCheckoutScreen()
            }
            confirmDialog.show()
        }.launchIn(lifecycleScope)

    }

    @SuppressLint("Recycle")
    private fun tvPriceAnimator(list: List<ProductWithCount>) {
        var sum = 0.0
        list.forEach {
            sum += it.productData.price * it.count
        }
        animator = ValueAnimator.ofFloat(oldSum.toFloat(), sum.toFloat()).apply {
            addUpdateListener {
                viewBinding.tvOrderValue.text =
                    (it.animatedValue as Float).toDouble().getFinanceType()
            }
            duration = 1000L
            start()
        }
        oldSum = sum
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (animator.isRunning)
            animator.cancel()
    }
}