package com.example.simplebank.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.simplebank.ui.product.AccountFragment
import com.example.simplebank.ui.product.CreditFragment

internal const val PRODUCT_OBJECT_ARG = "PRODUCT_OBJECT_ARG"

class ProductCollectionPagerAdapter(fragment: Fragment, private val items: Int) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = items

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> AccountFragment().apply {
            arguments = bundleOf(PRODUCT_OBJECT_ARG to position)
        }

        1 -> CreditFragment().apply {
            arguments = bundleOf(PRODUCT_OBJECT_ARG to position)
        }

        else -> throw IllegalStateException()
    }
}