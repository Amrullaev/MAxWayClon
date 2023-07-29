package com.amrullaev.maxwayclon.ui.orders.history
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.databinding.ListItemHistoryOrdersBinding
import com.amrullaev.maxwayclon.utils.extensions.getFinanceType
import com.amrullaev.maxwayclon.utils.extensions.getOrderName
import com.amrullaev.maxwayclon.utils.extensions.inflate


class HistoryOrderAdapter :
    ListAdapter<OrderData, HistoryOrderAdapter.ViewHolder>(activeItemCallback) {

    private var itemClickListener: ((OrderData) -> Unit)? = null

    fun setItemClickListener(block: (OrderData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemHistoryOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvOrderName.text = data.id.getOrderName()
                tvOrderPrice.text = data.allPrice.getFinanceType()
                tvOrderDate.text = data.createdDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemHistoryOrdersBinding.bind(parent.inflate(R.layout.list_item_active_orders))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private var activeItemCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem.id == newItem.id && oldItem.status == newItem.status

}