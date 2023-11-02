package com.example.ds.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.ds.R
import com.example.ds.model.ToolbarIconType
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.getDrawableRes
import com.example.sdk.extensions.getEnum
import com.google.android.material.imageview.ShapeableImageView

class SimpleBankToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val ivButton by viewProvider<ShapeableImageView>(R.id.iv_toolbar_button)

    init {
        inflate(context, R.layout.simple_bank_toolbar, this)
        setupView(attributeSet)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            context.withStyledAttributes(attributeSet, R.styleable.SimpleBankToolbar) {
                val toolbarIconType: ToolbarIconType =
                    getEnum(
                        R.styleable.SimpleBankToolbar_simple_bank_toolbar_button_type,
                        ToolbarIconType.AVATAR
                    )
                setIcon(toolbarIconType)
                setTransparency(
                    getBoolean(
                        R.styleable.SimpleBankToolbar_simple_bank_toolbar_transparent_enabled,
                        false
                    )
                )
            }
        }
    }

    private fun setIcon(type: ToolbarIconType) = when (type) {
        ToolbarIconType.AVATAR -> {
            ivButton.strokeWidth = 2F
            ivButton.setImageDrawable(resources.getDrawableRes(R.drawable.ds_icon_default_avatar))
        }

        ToolbarIconType.BACK -> {
            ivButton.strokeWidth = 0F
            ivButton.setImageDrawable(resources.getDrawableRes(R.drawable.ds_icon_arrow_back))
        }

        ToolbarIconType.CLOSE -> {
            ivButton.strokeWidth = 0F
            ivButton.setImageDrawable(resources.getDrawableRes(R.drawable.ds_icon_close))
        }
    }

    private fun setTransparency(enabled: Boolean) {
        if (enabled) {
            this.setBackgroundColor(
                resources.getColor(
                    R.color.ds_color_primary_transparent_50,
                    null
                )
            )
        } else {
            this.setBackgroundColor(
                resources.getColor(
                    R.color.ds_color_primary,
                    null
                )
            )
        }
    }
}