package com.example.simplebank.model

import android.os.Parcelable
import com.example.sdk.data.model.SummaryItemResponse
import com.example.sdk.data.model.SummaryType
import com.example.sdk.extensions.toMoney
import com.example.sdk.toolkit.Money
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryModel(
    val amount: Money,
    val type: SummaryType,
    val beneficiary: String,
    val code: String,
    val dueDate: String?
) : Parcelable {
    constructor(summaryItemResponse: SummaryItemResponse) : this(
        amount = summaryItemResponse.amount.toMoney(),
        type = SummaryType.valueOf(summaryItemResponse.type),
        beneficiary = summaryItemResponse.beneficiary,
        code = summaryItemResponse.code,
        dueDate = summaryItemResponse.dueDate
    )
}

sealed class Summary