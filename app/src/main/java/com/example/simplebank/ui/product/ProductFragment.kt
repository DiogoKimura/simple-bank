package com.example.simplebank.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sdk.delegates.viewProvider
import com.example.simplebank.R
import com.example.simplebank.adapter.ProductCollectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProductFragment : Fragment(R.layout.fragment_product) {

    private val vpProduct by viewProvider<ViewPager2>(R.id.product_view_pager)
    private val tlProduct by viewProvider<TabLayout>(R.id.product_tab_layout)

    private lateinit var vpAdapter: ProductCollectionPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        val listProduct =
            listOf(getString(R.string.title_account), getString(R.string.title_credit))

        vpAdapter = ProductCollectionPagerAdapter(this, listProduct.size)
        vpProduct.adapter = vpAdapter
        TabLayoutMediator(tlProduct, vpProduct) { tab, position ->
            tab.text = listProduct[position]
        }.attach()
    }
}