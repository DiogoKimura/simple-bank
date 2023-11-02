package com.example.sdk.data.repo

import com.example.sdk.data.model.SummaryItemResponse
import summaryDecodeMock

class SummaryRepository() {
    fun decode(code: String): SummaryItemResponse = summaryDecodeMock(code)

    fun makePayment(): Boolean = true
}