package com.amrullaev.maxwayclon.ui.home.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import by.kirich1409.viewbindingdelegate.viewBinding
import com.amrullaev.maxwayclon.R
import com.amrullaev.maxwayclon.databinding.FragmentProductDetailBinding
import com.amrullaev.maxwayclon.viewModels.ProductViewModel
import com.amrullaev.maxwayclon.viewModels.impl.ProductViewModelImpl
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val viewBinding: FragmentProductDetailBinding by viewBinding()

    private val viewModel: ProductViewModel by viewModels<ProductViewModelImpl>()

    private val navArgs: ProductDetailFragment by navArgs()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.productFlow.onEach { productWithCount ->

            val productData = productWithCount.productData

            viewBinding.apply {
                tvProductName.text = productData.name
                tvDescription.text = productData.desc
                tvProductCategoryName.text = productData.name
                Glide.with(requireContext())
                    .load(productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(imageFood)
                btnToBasket.apply {
                    if (productWithCount.count == 0) {
                        text = resources.getString(R.string.to_basket)
                        setTextColor(Color.WHITE)
                        setBackgroundResource(R.drawable.bg_to_basket_btn)
                    } else {
                        setTextColor(Color.BLACK)
                        setBackgroundResource(R.drawable.bg_in_basket_btn)
                        text = resources.getString(R.string.in_basket)
                    }
                    setOnClickListener {
                        if (productWithCount.count > 0) {
                            viewModel.openBasketScreen()
                        } else {
                            viewModel.productBasketClick()
                        }
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.setProduct(navArgs.productCount)
    }
}