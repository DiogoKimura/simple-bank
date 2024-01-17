package com.example.simplebank.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ds.adapter.TransactionContainerAdapter
import com.example.ds.widget.TransactionContainer
import com.example.sdk.delegates.viewProvider
import com.example.simplebank.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreditFragment : Fragment() {

    private val tcInvoice by viewProvider<TransactionContainer>(R.id.credit_tc_invoice)
    private val tcExpend by viewProvider<TransactionContainer>(R.id.credit_tc_expend)
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_credit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getAccountList()
    }

    private fun setupObservers() {
        viewModel.accountList.observe(viewLifecycleOwner) {
            it?.let {
                val tcAdapterInvoice = TransactionContainerAdapter(it)
                val tcAdapterExpend = TransactionContainerAdapter(it)
                tcInvoice.setAdapter(tcAdapterInvoice)
                tcInvoice.setTitle("Suas faturas")
                tcExpend.setAdapter(tcAdapterExpend)
                tcExpend.setTitle("Seus gastos")
            }
        }
    }

}