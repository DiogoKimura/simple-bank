package com.example.simplebank.ui.payment

import android.app.Activity
import android.content.Intent
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import com.example.sdk.delegates.viewProvider
import com.example.sdk.toolkit.scanner.SimpleScanner
import com.example.simplebank.R
import com.example.simplebank.contracts.SCANNER_CONTRACT_RESULT
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScannerActivity : AppCompatActivity(R.layout.activity_scanner) {

    private val previewView by viewProvider<PreviewView>(R.id.scanner_preview_view)
    private val progressBar by viewProvider<ProgressBar>(R.id.scanner_preview_progress_bar)
    private val viewModel: ScannerViewModel by viewModel()
    private var simpleScanner: SimpleScanner? = null

    override fun onStart() {
        super.onStart()
        startScanner()
    }

    override fun onResume() {
        super.onResume()
        simpleScanner?.start(previewView)
    }

    private fun startScanner() {
        if (simpleScanner == null) {
            simpleScanner = SimpleScanner(this).also {
                it.setupListener(::onBarcodeCallback)
            }
        }
    }

    private fun onBarcodeCallback(value: String?) = value?.let {
        val summaryItem = viewModel.decode(value)
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(SCANNER_CONTRACT_RESULT, summaryItem)
        })
        finish()
    }
}