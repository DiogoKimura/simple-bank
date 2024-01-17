package com.example.simplebank.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.sdk.delegates.viewProvider
import com.example.simplebank.R
import com.example.simplebank.model.SummaryModel

class SummaryAdapter(
    private val onDeleteCallback: (Int) -> Unit
) : ListAdapter<SummaryModel, SummaryViewHolder>(DiffUtilItemCallback) {

    object DiffUtilItemCallback : DiffUtil.ItemCallback<SummaryModel>() {
        override fun areItemsTheSame(oldItem: SummaryModel, newItem: SummaryModel) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: SummaryModel, newItem: SummaryModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SummaryViewHolder(
        parent,
        LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_container_item, parent, false)
    )

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) =
        holder.bind(getItem(position) as SummaryModel) {
            onDeleteCallback.invoke(position)
        }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAll(newList: List<SummaryModel>) {
        submitList(newList)
        notifyDataSetChanged()
    }

    fun add(newItem: SummaryModel) {
        val newItems = ArrayList(currentList)
        newItems.add(newItem)
        submitList(newItems)
        notifyItemChanged(currentList.lastIndex)
    }

    fun remove(position: Int) {
        val newItems = ArrayList(currentList)
        newItems.removeAt(position)
        submitList(newItems)
        notifyItemChanged(position)
    }
}

class SummaryViewHolder(
    private val parent: ViewGroup,
    view: View
) : RecyclerView.ViewHolder(view) {

    private val amount by viewProvider<AppCompatTextView>(R.id.payment_container_item_value)
    private val type by viewProvider<AppCompatTextView>(R.id.payment_container_item_type)
    private val beneficiary by viewProvider<AppCompatTextView>(R.id.payment_container_item_beneficiary)
    private val codeLabel by viewProvider<AppCompatTextView>(R.id.payment_container_item_code_label)
    private val codeValue by viewProvider<AppCompatTextView>(R.id.payment_container_item_code)
    private val dueDate by viewProvider<AppCompatTextView>(R.id.payment_container_item_due_date)

    // Region Delete
    private var showContainerDelete = false
    private val containerDelete by viewProvider<View>(R.id.payment_container_item_delete_layout)
    private val containerDeleteButton by viewProvider<AppCompatImageView>(R.id.payment_container_item_delete_circle)
    private val containerDeleteCancel by viewProvider<View>(R.id.payment_container_item_cancel_circle)

    init {
        showContainerDelete = false
        view.setOnClickListener { switchDeleteContainer() }
    }

    fun bind(model: SummaryModel, deleteCallback: () -> Unit) {
        amount.text = model.amount.formattedTextWithCurrency
        type.text = model.type.label
        beneficiary.text = model.beneficiary
        codeLabel.text = model.type.codeLabel
        codeValue.text = model.code
        model.dueDate?.let { dueDate.text = it }

        containerDeleteButton.setOnClickListener {
            switchDeleteContainer()
            deleteCallback.invoke()
        }
        containerDeleteCancel.setOnClickListener { switchDeleteContainer() }
    }

    private fun switchDeleteContainer() {
        TransitionManager.beginDelayedTransition(parent)

        showContainerDelete = !showContainerDelete

        containerDelete.isVisible = showContainerDelete
    }
}