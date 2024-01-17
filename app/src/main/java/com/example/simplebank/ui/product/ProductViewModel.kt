package com.example.simplebank.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.ds.model.TransactionItemModel
import com.example.sdk.data.repo.AccountRepository

class ProductViewModel(
    private val repository: AccountRepository
) : ViewModel() {

    private val _accountList = MutableLiveData<List<TransactionItemModel>?>()
    val accountList: LiveData<List<TransactionItemModel>?>
        get() = _accountList

    fun getAccountList() {
        _accountList.value = repository.getAccountList().map {
            it?.transactionList?.map(::TransactionItemModel)
        }.value
    }

}