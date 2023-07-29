import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentOrdersBinding
import com.amrullaev.maxwayclon.ui.orders.OrderPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private val viewBinding: FragmentOrdersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.pagerOrders.adapter = OrderPagerAdapter(requireActivity())
        viewBinding.apply {
            TabLayoutMediator(tabBarOrders, pagerOrders) { tab, pos ->
                tab.apply {
                    text = if (pos == 0) {
                        resources.getString(R.string.active)
                    } else {
                        resources.getString(R.string.history)
                    }
                }
            }.attach()

        }
    }


}