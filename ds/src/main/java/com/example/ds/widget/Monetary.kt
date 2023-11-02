package com.example.ds.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.withStyledAttributes
import com.example.ds.R
import com.example.ds.extensions.gone
import com.example.ds.extensions.visible
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.getEnum
import com.example.sdk.extensions.toMoney
import com.example.sdk.toolkit.Currency
import com.example.sdk.toolkit.Money

class Monetary @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val tvMonetaryCurrency by viewProvider<AppCompatTextView>(R.id.text_view_monetary_currency)
    private val tvMonetaryValue by viewProvider<AppCompatTextView>(R.id.text_view_monetary_value)
    private val tvMonetaryCents by viewProvider<AppCompatTextView>(R.id.text_view_monetary_cents)

    private val groupUnmasked by viewProvider<Group>(R.id.group_monetary_unmasked)
    private val groupMasked by viewProvider<Group>(R.id.group_monetary_masked)

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.monetary, this, true)
        setupView(attributeSet)
    }

    fun setMoney(money: Money) = with(money) {
        tvMonetaryValue.text = formattedValue
        tvMonetaryCents.text = formattedCents
        tvMonetaryCurrency.text = currency.symbol
    }

    fun setMasked(masked: Boolean) {
        if (masked) {
            groupMasked.visible()
            groupUnmasked.gone()
        } else {
            groupMasked.gone()
            groupUnmasked.visible()
        }
    }

    fun setCurrency(currency: Currency) {
        tvMonetaryCurrency.text = currency.symbol
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            context.withStyledAttributes(it, R.styleable.Monetary) {
                setCurrency(getEnum(R.styleable.Monetary_monetary_currency, Currency.BRL))
                getString(R.styleable.Monetary_monetary_amount)?.let(::setAmount)
                setMasked(getBoolean(R.styleable.Monetary_monetary_masked, false))
            }
        }
    }

    private fun setAmount(value: String) {
        setMoney(value.toMoney())
    }
}