package com.example.simplebank.ui.payment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ds.widget.Monetary
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.showToastLong
import com.example.sdk.extensions.toMoney
import com.example.simplebank.R
import com.example.simplebank.adapter.SummaryAdapter
import com.example.simplebank.contracts.SimpleScannerContract
import com.example.simplebank.goToHome
import com.example.simplebank.model.SummaryModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private val args by navArgs<SummaryFragmentArgs>()
    private val viewModel: SummaryViewModel by viewModel()

    private val tvTotalAmount by viewProvider<Monetary>(R.id.summary_monetary)
    private val rvItemContainer by viewProvider<RecyclerView>(R.id.summary_payment_container)
    private val tvMakePayment by viewProvider<TextView>(R.id.summary_tv_make_payment)
    private val tvAddItem by viewProvider<ImageView>(R.id.summary_iv_add_payment)
    private val summaryAdapter = SummaryAdapter(::onDeleteCallback)

    private val startScannerForResult =
        registerForActivityResult(SimpleScannerContract(::onCancelledCallback), ::onBarcodeCallback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservable()
        setupView()
    }

    private fun setupView() {
        updateSummaryItemList()
        setupItemContainer()
        setupButtons()
    }

    private fun setupObservable() {
        viewModel.summaryList.observe(viewLifecycleOwner) {
            summaryAdapter.updateAll(it)
            tvTotalAmount.setMoney(viewModel.getTotalAmount().toString().toMoney())
        }
    }

    private fun setupItemContainer() {
        rvItemContainer.apply {
            adapter = summaryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun updateSummaryItemList() = args.summaryItem.let {
        viewModel.addSummaryItem(it, ::onCannotAddCallback)
    }

    private fun setupButtons() {
        tvAddItem.setOnClickListener {
            startScannerForResult.launch(0)
        }
        tvMakePayment.setOnClickListener {
            val isSuccessful = viewModel.makePayment()
            if (isSuccessful) {
                requireContext().showToastLong(R.string.payment_summary_make_payment)
            }
            requireContext().goToHome()
            requireActivity().finish()
        }
    }

    private fun onDeleteCallback(position: Int) {
        viewModel.removeSummaryItem(position)
    }

    private fun onBarcodeCallback(value: SummaryModel?) = value?.let {
        viewModel.addSummaryItem(it, ::onCannotAddCallback)
    }

    private fun onCancelledCallback() =
        requireContext().showToastLong(R.string.payment_scanner_cancelled)

    private fun onCannotAddCallback() =
        requireContext().showToastLong(R.string.payment_summary_code_already_inserted)
}