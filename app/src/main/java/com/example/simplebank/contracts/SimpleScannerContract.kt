package com.example.simplebank.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import com.example.simplebank.model.SummaryModel
import com.example.simplebank.ui.payment.ScannerActivity

const val SCANNER_CONTRACT_RESULT = "SCANNER_CONTRACT_RESULT"

class SimpleScannerContract(
    private val cancelledCallback: () -> Unit
) : ActivityResultContract<Int, SummaryModel?>() {
    override fun createIntent(context: Context, input: Int) =
        Intent(context, ScannerActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): SummaryModel? {
        if (resultCode != Activity.RESULT_OK) {
            cancelledCallback.invoke()
            return null
        }

        return intent?.getParcelableExtra(SCANNER_CONTRACT_RESULT)
    }
}