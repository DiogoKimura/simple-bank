package com.example.ds.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ds.R
import com.example.ds.adapter.TransactionContainerAdapter
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.getDrawableRes

class TransactionContainer @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val rv by viewProvider<RecyclerView>(R.id.transaction_container_rv)
    private val tv by viewProvider<TextView>(R.id.transaction_container_tv)

    init {
        LayoutInflater.from(context).inflate(R.layout.transaction_container, this)
        setupRecycleView()
    }

    fun setTitle(title: String) {
        tv.text = title
    }

    fun setAdapter(adapter: TransactionContainerAdapter) {
        rv.adapter = adapter
    }

    private fun setupRecycleView() {
        rv.layoutManager = LinearLayoutManager(context)
        rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

}