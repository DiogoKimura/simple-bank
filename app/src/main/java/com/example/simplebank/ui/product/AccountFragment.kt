package com.example.simplebank.ui.product

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.ds.adapter.TransactionContainerAdapter
import com.example.ds.extensions.gone
import com.example.ds.widget.TransactionContainer
import com.example.sdk.delegates.viewProvider
import com.example.simplebank.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccountFragment : Fragment(R.layout.fragment_account) {

    private val viewModel: ProductViewModel by viewModel()
    private val transactionContainer by viewProvider<TransactionContainer>(R.id.account_transaction_container)
    private lateinit var transactionContainerAdapter: TransactionContainerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAccountList().observe(viewLifecycleOwner) {
            it?.let {
                transactionContainerAdapter = TransactionContainerAdapter(it)
                transactionContainer.apply {
                    setAdapter(transactionContainerAdapter)
                    setTitle(getString(R.string.title_expend))
                    setLoading(false)
                }
            }
        }
    }
}