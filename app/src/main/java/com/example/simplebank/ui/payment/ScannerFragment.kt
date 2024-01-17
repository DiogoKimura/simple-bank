package com.example.simplebank.ui.payment

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simplebank.contracts.SimpleScannerContract
import com.example.simplebank.model.SummaryModel

class ScannerFragment : Fragment() {

    private val startScannerForResult =
        registerForActivityResult(
            SimpleScannerContract(
                ::onCancelledCallback,
                ::onBarcodeInputCallback
            ), ::onBarcodeCallback
        )

    override fun onResume() {
        super.onResume()
        startScannerForResult.launch(0)
    }

    private fun onBarcodeCallback(value: SummaryModel?) = value?.let {
        val action = ScannerFragmentDirections.actionScannerToSummary(it)
        findNavController().navigate(action)
    }

    private fun onCancelledCallback() {
        requireActivity().finish()
    }

    private fun onBarcodeInputCallback() {
        findNavController().navigate(ScannerFragmentDirections.actionScannerToInput())
    }
}