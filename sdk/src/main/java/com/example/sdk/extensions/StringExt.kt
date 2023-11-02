package com.example.sdk.extensions

import com.example.sdk.toolkit.Money

fun String.toMoney() = Money(this)