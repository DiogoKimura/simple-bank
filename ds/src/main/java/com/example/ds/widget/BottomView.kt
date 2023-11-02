package com.example.ds.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import com.example.ds.BuildConfig
import com.example.ds.R
import com.example.sdk.delegates.viewProvider
import java.io.File


class BottomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val btnPayment by viewProvider<ImageView>(R.id.btn_payment)
    val btnTransfer by viewProvider<ImageView>(R.id.btn_transfer)
    val btnHelp by viewProvider<ImageView>(R.id.btn_help)

    init {
        inflate(context, R.layout.bottom_view, this)
    }
}