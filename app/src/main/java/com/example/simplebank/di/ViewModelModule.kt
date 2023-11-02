package com.example.simplebank.di

import com.example.simplebank.ui.payment.ScannerViewModel
import com.example.simplebank.ui.payment.SummaryViewModel
import com.example.simplebank.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
    viewModel { ScannerViewModel(get()) }
    viewModel { SummaryViewModel(get()) }
}