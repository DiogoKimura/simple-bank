package com.example.simplebank.ui.payment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.camera.view.PreviewView
import com.example.ds.widget.SimpleBankToolbar
import com.example.sdk.delegates.viewProvider
import com.example.sdk.toolkit.scanner.SimpleScanner
import com.example.simplebank.R
import com.example.simplebank.contracts.RESULT_INPUT_BARCODE
import com.example.simplebank.contracts.SCANNER_CONTRACT_RESULT
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScannerActivity : AppCompatActivity(R.layout.activity_scanner) {

    private val toolbar by viewProvider<SimpleBankToolbar>(R.id.scanner_toolbar)
    private val previewView by viewProvider<PreviewView>(R.id.scanner_preview_view)
    private val progressBar by viewProvider<ProgressBar>(R.id.scanner_preview_progress_bar)
    private val inputButton by viewProvider<AppCompatTextView>(R.id.summary_tv_input_code)
    private val viewModel: ScannerViewModel by viewModel()
    private var simpleScanner: SimpleScanner? = null

    override fun onStart() {
        super.onStart()
        startScanner()
        setupListeners()
        inputButton.setOnClickListener(::onBarcodeInputCallback)
    }

    override fun onResume() {
        super.onResume()
        simpleScanner?.start(previewView)
    }

    private fun setupListeners() {
        toolbar.setOnClickListener { onBarcodeCallback(null) }
    }

    private fun startScanner() {
        if (simpleScanner == null) {
            simpleScanner = SimpleScanner(this).also {
                it.setupListener(::onBarcodeCallback)
            }
        }
    }

    private fun onBarcodeCallback(value: String?) = value?.let {
        viewModel.decode(value).observe(this) {
            it?.let {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(SCANNER_CONTRACT_RESULT, it)
                })
                finish()
            }
        }
    }

    private fun onBarcodeInputCallback(v: View) {
        setResult(RESULT_INPUT_BARCODE, Intent())
        finish()
    }
}