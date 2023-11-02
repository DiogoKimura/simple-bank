package com.example.sdk.data.model

enum class SummaryType(val label: String, val codeLabel: String) {
    PIX("Qr Code Pix", "qr code"),
    BILLET("Boleto bancário", "código de barras")
}