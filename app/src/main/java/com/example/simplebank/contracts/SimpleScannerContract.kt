package com.example.simplebank.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import com.example.simplebank.model.SummaryModel
import com.example.simplebank.ui.payment.ScannerActivity

const val SCANNER_CONTRACT_RESULT = "SCANNER_CONTRACT_RESULT"
const val RESULT_INPUT_BARCODE = 99
class SimpleScannerContract(
    private val cancelledCallback: () -> Unit,
    private val inputBarcodeCallback: () -> Unit
) : ActivityResultContract<Int, SummaryModel?>() {
    override fun createIntent(context: Context, input: Int) =
        Intent(context, ScannerActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): SummaryModel? {
        if (resultCode == RESULT_INPUT_BARCODE) {
            inputBarcodeCallback.invoke()
            return null
        } else if (resultCode != Activity.RESULT_OK) {
            cancelledCallback.invoke()
            return null
        }

        return intent?.getParcelableExtra(SCANNER_CONTRACT_RESULT)
    }
}