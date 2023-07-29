package com.amrullaev.maxwayclon.ui.orders.active

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.OrderData
import com.amrullaev.maxwayclon.data.models.enums.OrderType
import com.amrullaev.maxwayclon.databinding.ListItemActiveOrdersBinding
import com.amrullaev.maxwayclon.utils.extensions.getOrderName
import com.amrullaev.maxwayclon.utils.extensions.inflate

class ActiveOrderAdapter :
    ListAdapter<OrderData, ActiveOrderAdapter.ViewHolder>(activeItemCallback) {

    inner class ViewHolder(private val binding: ListItemActiveOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvOrderName.text = data.id.getOrderName()
                tvOrderStatus.text = data.status.name
                val count = if (data.orderType == OrderType.SIMPLE) 3 else 4
                orderStepView.setStepsNumber(count)
                orderStepView.go(data.status.ordinal+1, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemActiveOrdersBinding.bind(parent.inflate(R.layout.list_item_active_orders))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private var activeItemCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem.id == newItem.id && oldItem.status == newItem.status

}