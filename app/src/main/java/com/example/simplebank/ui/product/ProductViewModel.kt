package com.example.simplebank.ui.product

import androidx.lifecycle.ViewModel
import com.example.ds.model.TransactionItemModel
import com.example.sdk.data.repo.AccountRepository

class ProductViewModel(
    private val repository: AccountRepository
) : ViewModel() {

    fun getAccountList() = repository.getAccountList().transactionList.map(::TransactionItemModel)

}