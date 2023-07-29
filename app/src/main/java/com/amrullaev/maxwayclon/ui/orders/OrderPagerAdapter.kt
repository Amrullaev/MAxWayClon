package com.amrullaev.maxwayclon.ui.orders

import HistoryOrdersFragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amrullaev.maxwayclon.ui.orders.active.ActiveOrdersPage

class OrderPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) =
        if (position == 0) ActiveOrdersPage()
        else HistoryOrdersFragment()
}