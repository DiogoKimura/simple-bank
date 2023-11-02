package com.example.sdk.data.model

class TransactionListResponse(
    val transactionList: List<TransactionItemResponse>
)

class TransactionItemResponse(
    val title: String,
    val subtitle: String,
    val amount: String,
    val labelStatus: TransactionItemLabel?,
    val descriptionTittle: String,
    val descriptionSubtitle: String,
    val descriptionLabel: String,
    val descriptionReceiptId: String
)

enum class TransactionItemLabel {
    OPEN, EXPIRING, EXPIRED
}