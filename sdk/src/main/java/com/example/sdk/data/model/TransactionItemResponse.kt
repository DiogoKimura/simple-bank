package com.example.sdk.data.model

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TransactionListResponse(
    val transactionList: List<TransactionItemResponse>
)

@JsonClass(generateAdapter = true)
class TransactionItemResponse(
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "amount") val amount: String,
    @Json(name = "label_status") val labelStatus: TransactionItemLabel?,
    @Json(name = "description_title") val descriptionTittle: String,
    @Json(name = "description_subtitle") val descriptionSubtitle: String,
    @Json(name = "description_label") val descriptionLabel: String,
    @Json(name = "description_receipt_id") val descriptionReceiptId: String
)

enum class TransactionItemLabel {
    OPEN, EXPIRING, EXPIRED
}