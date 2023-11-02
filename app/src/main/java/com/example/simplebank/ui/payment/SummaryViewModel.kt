package com.example.simplebank.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdk.data.repo.SummaryRepository
import com.example.simplebank.model.SummaryModel
import java.math.BigDecimal
import java.text.FieldPosition

class SummaryViewModel(
    private val repository: SummaryRepository
) : ViewModel() {
    private val _summaryList = MutableLiveData<MutableList<SummaryModel>>()
    val summaryList: LiveData<MutableList<SummaryModel>>
        get() = _summaryList

    fun getTotalAmount(): BigDecimal {
        var totalAmount = BigDecimal.ZERO
        summaryList.value?.forEach {
            totalAmount += it.amount.bigDecimal
        }

        return totalAmount
    }

    fun addSummaryItem(item: SummaryModel, cannotAddCallback: () -> Unit) {
        _summaryList.value =
            _summaryList.value?.apply {
                val canAdd = !map { it.code }.contains(item.code)
                if (canAdd) add(item)
                else cannotAddCallback.invoke()
            } ?: mutableListOf<SummaryModel>().apply { add(item) }
    }

    fun removeSummaryItem(position: Int) {
        _summaryList.value =
            _summaryList.value?.apply {
                removeAt(position)
            }
    }

    fun makePayment() : Boolean = repository.makePayment()
}