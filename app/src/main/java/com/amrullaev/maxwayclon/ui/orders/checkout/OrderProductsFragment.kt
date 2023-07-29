
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.data.models.dto.OrderDto
import com.amrullaev.maxwayclon.data.models.enums.OrderType
import com.amrullaev.maxwayclon.databinding.FragmentOrderProductsBinding
import com.amrullaev.maxwayclon.databinding.ListItemOrderProductsBinding
import com.amrullaev.maxwayclon.ui.dialogs.ConfirmDialog
import com.amrullaev.maxwayclon.utils.Basket
import com.amrullaev.maxwayclon.utils.extensions.*
import com.amrullaev.maxwayclon.viewModels.OrderProductsViewModel
import com.amrullaev.maxwayclon.viewModels.impl.OrderProductsViewModelImpl
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OrderProductsFragment : Fragment(R.layout.fragment_order_products) {

    private val viewModel: OrderProductsViewModel by viewModels<OrderProductsViewModelImpl>()

    private val viewBinding: FragmentOrderProductsBinding by viewBinding()

    private var orderType: OrderType = OrderType.SIMPLE

    private lateinit var products: List<ProductWithCount>

    private var summ = 0.0

    private var address: LatLng? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.successFlow.onEach {
            showSuccessDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.deliveryAddress.onEach {
            viewBinding.tvMapDeliveryAddress.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isDeliveryFlow.onEach {
            if (it) {
                orderType = OrderType.DELIVERY
                viewBinding.imageCheckDelivery.visible()
                viewBinding.imageCheckOnTheWay.inVisible()
                viewBinding.containerMap.visible()
            } else {
                orderType = OrderType.SIMPLE
                viewBinding.imageCheckDelivery.inVisible()
                viewBinding.imageCheckOnTheWay.visible()
                viewBinding.containerMap.gone()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.btnConfirm.setOnClickListener {
            val confirmDialog = ConfirmDialog(requireContext())
            confirmDialog.setConfirmClickListener {
                viewModel.orderConfirmClick(
                    OrderDto(
                        products.map { it.toOrderItem() }.toHashSet(),
                        comment = viewBinding.inputComment.text.toString(),
                        orderType,
                        address = if (address != null) address?.toAddress() else null,
                    )
                )
            }
            confirmDialog.show()
        }

        viewBinding.apply {

            containerOnTheWay.setOnClickListener { viewModel.setDeliveryMethod(false) }

            containerDelivery.setOnClickListener { viewModel.setDeliveryMethod(true) }

            containerMap.setOnClickListener {
                viewModel.navigateToMap()
            }
        }

        Basket.locationLiveData.observe(viewLifecycleOwner) {
            address = it.second
            viewBinding.tvMapDeliveryAddress.text = it.first
        }

        Basket.productsListLiveData.observe(viewLifecycleOwner) {
            loadOrders(it.filter { it.count > 0 })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadOrders(list: List<ProductWithCount>) {
        products = list
        summ = 0.0
        for (i in list) {
            summ += i.count * i.productData.price
            val orderBinding = ListItemOrderProductsBinding.inflate(layoutInflater)
            orderBinding.tvOrderProductNameWithCount.text = "${i.productData.name} ${i.count}x"
            orderBinding.tvOrderProductPrice.text = i.productData.price.getFinanceType()
            viewBinding.productsContainer.addView(orderBinding.root)
        }
        viewBinding.tvOrderAllSum.text = summ.getFinanceType()
    }

}