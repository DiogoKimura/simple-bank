package com.example.ds.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.ds.R
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.getDrawableRes
import com.example.sdk.extensions.getEnum
import com.example.sdk.extensions.toMoney
import com.example.sdk.toolkit.Currency

class MoneyBox @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleRef: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRef) {

    private val tvLabel by viewProvider<AppCompatTextView>(R.id.text_view_money_box_label)
    private val icVisibility by viewProvider<AppCompatImageView>(R.id.image_view_money_box_visibility)
    private val monetary by viewProvider<Monetary>(R.id.monetary_money_box)

    private var isMasked: Boolean = false

    init {
        inflate(context, R.layout.money_box, this)
        setupView(attributeSet)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            context.withStyledAttributes(it, R.styleable.MoneyBox) {
                getString(R.styleable.MoneyBox_money_box_amount)?.let(::setMoney)
                getEnum(R.styleable.MoneyBox_money_box_currency, Currency.BRL).let(::setCurrency)
                getString(R.styleable.MoneyBox_money_box_label)?.let(::setLabel)
                isMasked = getBoolean(R.styleable.MoneyBox_money_box_masked, false)
            }
        }
        setMask()
        setOnClick()
    }

    fun setMoney(amount: String) {
        monetary.setMoney(amount.toMoney())
    }

    fun setCurrency(currency: Currency) {
        monetary.setCurrency(currency)
    }

    fun setLabel(label: String) {
        tvLabel.text = label
    }

    private fun setOnClick() = icVisibility.setOnClickListener {
        isMasked = !isMasked
        setMask()
    }

    private fun setMask() = if (isMasked) {
        monetary.setMasked(true)
        icVisibility.setImageDrawable(resources.getDrawableRes(R.drawable.ds_icon_visibility_off))
    } else {
        monetary.setMasked(false)
        icVisibility.setImageDrawable(resources.getDrawableRes(R.drawable.ds_icon_visibility))
    }
}