package com.example.sdk.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sdk.data.api.AccountApi
import com.example.sdk.data.model.TransactionListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(private val api: AccountApi) {

    fun getAccountList(): LiveData<TransactionListResponse?> {
        val liveData = MutableLiveData<TransactionListResponse?>()

        api.getAccountTransactions().enqueue(object : Callback<TransactionListResponse?> {
            override fun onResponse(
                call: Call<TransactionListResponse?>,
                response: Response<TransactionListResponse?>
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<TransactionListResponse?>, t: Throwable) {
                liveData.postValue(null)
            }

        })

        return liveData
    }

}