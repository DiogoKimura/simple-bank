package com.example.sdk.data.model

import com.example.sdk.toolkit.Money
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SummaryItemResponse(
    @Json(name = "amount") val amount: String,
    @Json(name = "type") val type: String,
    @Json(name = "beneficiary") val beneficiary: String,
    @Json(name = "code") val code: String,
    @Json(name = "due_date") val dueDate: String?
)