package com.example.simplebank.ui.payment

import androidx.lifecycle.ViewModel
import com.example.sdk.data.repo.SummaryRepository
import com.example.simplebank.model.SummaryModel

class ScannerViewModel(private val repository: SummaryRepository) : ViewModel() {
    fun decode(code: String) = repository.decode(code).run { SummaryModel(this) }
}