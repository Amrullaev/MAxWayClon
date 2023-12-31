package com.amrullaev.maxwayclon.ui.home

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.ProductWithCount
import com.amrullaev.maxwayclon.databinding.ListItemProductsBinding
import com.amrullaev.maxwayclon.utils.extensions.getFinanceType
import com.amrullaev.maxwayclon.utils.extensions.inflate
import com.bumptech.glide.Glide

class ProductsAdapter : ListAdapter<ProductWithCount, ProductsAdapter.ViewHolder>(itemCallback) {
    private var itemBasketClickListener: ((ProductWithCount) -> Unit)? = null

    fun setItemBasketClickListener(block: (ProductWithCount) -> Unit) {
        itemBasketClickListener = block
    }

    private var openBasketScreenListener: (() -> Unit)? = null

    fun setOpenBasketClickListener(block: () -> Unit) {
        openBasketScreenListener = block
    }

    private var openProductDetailListener: ((ProductWithCount) -> Unit)? = null

    fun setOpenProductDetailsListener(block: (ProductWithCount) -> Unit) {
        openProductDetailListener = block
    }

    inner class ViewHolder(private val binding: ListItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                btnBasket.setOnClickListener {
                    val data = getItem(absoluteAdapterPosition)
                    if (data.count > 0) {
                        openBasketScreenListener?.invoke()
                    } else
                        itemBasketClickListener?.invoke(data)
                }
                root.setOnClickListener {
                    openProductDetailListener?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvProductName.text = data.productData.name
                tvProductName.setSingleLine()
                tvProductPrice.text = data.productData.price.getFinanceType()
                btnBasket.apply {
                    if (data.count == 0) {
                        text = resources.getString(R.string.to_basket)
                        setBackgroundResource(R.drawable.bg_to_basket_btn)
                        setTextColor(Color.parseColor("#ffffff"))
                    } else {
                        setTextColor(Color.parseColor("#000000"))
                        setBackgroundResource(R.drawable.bg_in_basket_btn)
                        text = resources.getString(R.string.in_basket)
                    }
                }
                Glide.with(binding.imageProductsItem.context)
                    .load(data.productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(binding.imageProductsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemProductsBinding.bind(parent.inflate(R.layout.list_item_products))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

 val itemCallback = object : DiffUtil.ItemCallback<ProductWithCount>() {
    override fun areItemsTheSame(oldItem: ProductWithCount, newItem: ProductWithCount) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductWithCount, newItem: ProductWithCount): Boolean =
        oldItem.count == newItem.count && oldItem.productData.id == newItem.productData.id

}