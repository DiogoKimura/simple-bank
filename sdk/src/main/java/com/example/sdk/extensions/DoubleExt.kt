package com.example.sdk.extensions

import com.example.sdk.toolkit.Money
import java.math.RoundingMode

val Double.roundedString
    get() = toBigDecimal().setScale(2, RoundingMode.FLOOR).toString()

fun Double.toMoney() = Money(this)