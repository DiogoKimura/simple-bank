package com.example.simplebank.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.sdk.data.repo.SummaryRepository
import com.example.simplebank.model.SummaryModel

class ScannerViewModel(private val repository: SummaryRepository) : ViewModel() {
    fun decode(code: String): LiveData<SummaryModel?> = repository.decode(code).map {
        it?.let { SummaryModel(it) }
    }
}