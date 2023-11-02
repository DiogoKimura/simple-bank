package com.example.sdk.data.model

import com.example.sdk.toolkit.Money

class SummaryItemResponse(
    val amount: Money,
    val type: SummaryType,
    val beneficiary: String,
    val code: String,
    val dueDate: String
)