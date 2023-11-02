package com.example.ds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.ds.R
import com.example.ds.extensions.gone
import com.example.ds.extensions.invisible
import com.example.ds.extensions.setVisibleOrGone
import com.example.ds.extensions.visible
import com.example.ds.model.TransactionItemModel
import com.example.sdk.delegates.viewProvider
import com.example.sdk.extensions.getDrawableRes


class TransactionContainerAdapter(
    private val listItem: List<TransactionItemModel>
) : RecyclerView.Adapter<TransactionContainerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionContainerViewHolder(
            parent,
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        )

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: TransactionContainerViewHolder, position: Int) {
        holder.bind(listItem[position])
    }
}

class TransactionContainerViewHolder(
    private val parent: ViewGroup,
    view: View
) : RecyclerView.ViewHolder(view) {
    private val date by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_date)
    private val label by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_label)
    private val value by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_value)
    private val arrow by viewProvider<AppCompatImageView>(R.id.iv_transaction_item_arrow)

    private val expandGroup by viewProvider<Group>(R.id.group_transaction_item_expand)
    private val expandTitle by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_expand_title)
    private val expandDescription by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_expand_desc)
    private val expandLabel by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_expand_label)
    private val expandLink by viewProvider<AppCompatTextView>(R.id.tv_transaction_item_expand_link)

    private var isExpanded = false
    private var canExpand = false

    fun bind(model: TransactionItemModel) {
        date.text = model.date
        label.text = model.label
        value.text = model.value

        expandTitle.text = model.expandTitle
        expandDescription.text = model.expandDescription
        expandLabel.text = model.expandLabel
        expandLink.text = "Comprovante"
        updateExpandedGroup(model.isExpanded)
        canExpand = model.canExpand

        arrow.setOnClickListener {
            // Animate every layout change with default configurations
            TransitionManager.beginDelayedTransition(parent)
            updateExpandedGroup(!isExpanded)
        }
    }

    private fun updateExpandedGroup(expand: Boolean) {
        isExpanded = expand

        if (isExpanded && canExpand) {
            arrow.setImageDrawable(parent.resources.getDrawableRes(R.drawable.ds_icon_arrow_down))
        } else {
            arrow.setImageDrawable(parent.resources.getDrawableRes(R.drawable.ds_icon_arrow_right))
        }

        expandGroup.setVisibleOrGone(isExpanded)
    }
}