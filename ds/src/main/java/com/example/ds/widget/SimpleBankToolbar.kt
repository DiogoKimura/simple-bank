package com.example.ds.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.ds.R
import com.example.sdk.delegates.viewProvider
import com.google.android.material.imageview.ShapeableImageView

class SimpleBankToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val shapeableImageView by viewProvider<ShapeableImageView>(R.id.ds_toolbar_view_shapeable_iv)
    private val imageView by viewProvider<ImageView>(R.id.ds_toolbar_view_iv)

    init {
        inflate(context, R.layout.sb_toolbar_view, this)
        setupView(attributeSet)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            context.withStyledAttributes(attributeSet, R.styleable.SimpleBankToolbar) {
                setShapeableImageViewType(getInt(R.styleable.SimpleBankToolbar_button_type, 0))
            }
        }
    }

    private fun setShapeableImageViewType(type: Int) {
        if (type == 0) {
            shapeableImageView.strokeWidth = 2F
            shapeableImageView.setImageDrawable(context.getDrawable(R.drawable.ds_default_avatar))
        } else if (type == 1) {
            shapeableImageView.strokeWidth = 0F
            shapeableImageView.setImageDrawable(context.getDrawable(R.drawable.ds_arrow_back))
        } else if (type == 2) {
            shapeableImageView.strokeWidth = 0F
            shapeableImageView.setImageDrawable(context.getDrawable(R.drawable.ds_close))
        }
    }

}