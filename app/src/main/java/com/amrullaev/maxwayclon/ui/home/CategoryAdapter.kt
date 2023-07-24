package com.amrullaev.maxwayclon.ui.home

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.data.models.CategoryData
import com.amrullaev.maxwayclon.databinding.ListItemCategoryBinding
import com.amrullaev.maxwayclon.utils.extensions.inflate

class CategoryAdapter :
    ListAdapter<CategoryData, CategoryAdapter.ViewHolder>(categoryItemCallback) {

    var selectedPos = -1

    private var categorySelectedListener: ((CategoryData) -> Unit)? = null

    fun setCategoryListener(block: (CategoryData) -> Unit) {
        categorySelectedListener = block
    }

    inner class ViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvCategoryName.setOnClickListener {
                selectedPos = if (selectedPos == absoluteAdapterPosition) {
                    -1
                } else {
                    notifyItemChanged(selectedPos)
                    absoluteAdapterPosition
                }
                categorySelectedListener?.invoke(getItem(absoluteAdapterPosition))
                notifyItemChanged(absoluteAdapterPosition)
            }
        }

        fun onBind() {
            binding.apply {
                tvCategoryName.apply {
                    if (selectedPos == absoluteAdapterPosition) {
                        setBackgroundResource(R.drawable.bg_category_item_selected)
                        setTextColor(Color.parseColor("#ffffff"))
                    } else {
                        setBackgroundResource(R.drawable.bg_category_unselected)
                        setTextColor(Color.parseColor("#000000"))
                    }
                    text = getItem(absoluteAdapterPosition).name
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemCategoryBinding.bind(parent.inflate(R.layout.list_item_category))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

    private val categoryItemCallback = object : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem.id == newItem.id && oldItem.name == newItem.name

    }
