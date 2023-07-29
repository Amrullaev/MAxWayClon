package com.amrullaev.maxwayclon.ui

import OrdersFragment
import ProfileFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amrullaev.maxwayclon.ui.home.HomeFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HomeFragment()
            1 -> OrdersFragment()
            else -> ProfileFragment()
        }
}