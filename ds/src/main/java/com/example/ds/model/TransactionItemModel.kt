package com.example.ds.model

import android.text.SpannableString
import com.example.sdk.data.model.TransactionItemResponse

class TransactionItemModel(
    val date: String,
    val label: String,
    val value: SpannableString,
    val isExpanded: Boolean,
    val canExpand: Boolean,
    val expandTitle: String,
    val expandDescription: String,
    val expandLabel: String,
    val expandLinkText: String
) {
    constructor(response: TransactionItemResponse): this(
        date = response.title,
        label = response.subtitle,
        value = SpannableString.valueOf(response.amount),
        isExpanded = false,
        canExpand = true,
        expandTitle = response.descriptionLabel,
        expandDescription = response.descriptionSubtitle,
        expandLabel = response.descriptionLabel,
        expandLinkText = response.descriptionReceiptId
    )
}