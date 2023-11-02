package com.example.simplebank

import android.content.Context
import android.content.Intent
import com.example.simplebank.ui.MainActivity

fun Context.goToHome() = startActivity(
    Intent(this, MainActivity::class.java)
)