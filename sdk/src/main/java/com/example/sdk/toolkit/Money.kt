package com.example.sdk.toolkit

import android.os.Parcelable
import com.example.sdk.extensions.roundedString
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Money(
    private val amount: String,
    private val cents: String,
    val currency: Currency = Currency.BRL,
    val bigDecimal: BigDecimal
) : Parcelable {

    constructor(value: Int, currency: Currency = Currency.BRL) : this(
        amount = value.toString(),
        cents = "00", // Int always without cents
        currency = currency,
        bigDecimal = value.toBigDecimal()
    )

    constructor(value: Double, currency: Currency = Currency.BRL) : this(
        amount = value.roundedString.takeWhile(Character::isDigit),
        cents = value.roundedString.takeLastWhile(Character::isDigit),
        currency = currency,
        bigDecimal = value.toBigDecimal()
    )

    constructor(value: Float, currency: Currency = Currency.BRL) : this(
        amount = value.roundedString.takeWhile(Character::isDigit),
        cents = value.roundedString.takeLastWhile(Character::isDigit),
        currency = currency,
        bigDecimal = value.toBigDecimal()
    )

    constructor(value: String, currency: Currency = Currency.BRL) : this(
        amount = value.takeWhile(Character::isDigit),
        cents = value.takeLastWhile(Character::isDigit).takeIf { it.contains(".") } ?: "00",
        currency = currency,
        bigDecimal = value.takeWhile(Character::isDigit).toBigDecimal().apply {
            add((value.takeLastWhile(Character::isDigit).takeIf { it.contains(".") }
                ?: "00").toFloat().div(100).toBigDecimal())
        }
    )

    private val fullAmount
        get() = "$amount$cents"

    val textWithCurrency
        get() = "${currency.symbol} $amount,$cents"
    val textWithoutCurrency
        get() = "$amount,$cents"

    val formattedValue
        get() = amount.reversed().chunked(3).joinToString(".").reversed()
    val formattedCents
        get() = ",$cents"
    val formattedTextWithCurrency
        get() = "${currency.symbol} $formattedValue$formattedCents"
    val formattedTextWithoutCurrency
        get() = "$formattedValue$formattedCents"
}

enum class Currency(val symbol: String) {
    BRL("R$"),
    US("$"),
    EUR("€");
}