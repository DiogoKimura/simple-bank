package com.example.simplebank.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.ds.model.TransactionItemModel
import com.example.sdk.data.repo.AccountRepository

class ProductViewModel(
    private val repository: AccountRepository
) : ViewModel() {

    fun getAccountList(): LiveData<List<TransactionItemModel>?> = repository.getAccountList().map {
        it?.map(::TransactionItemModel)
    }
}