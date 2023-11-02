package com.example.sdk.data.repo

import com.example.sdk.data.model.TransactionListResponse
import transactionListMock

class AccountRepository {

    fun getAccountList(): TransactionListResponse = transactionListMock

}