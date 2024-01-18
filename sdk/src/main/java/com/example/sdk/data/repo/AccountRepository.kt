package com.example.sdk.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sdk.data.api.AccountApi
import com.example.sdk.data.model.TransactionItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(private val api: AccountApi) {

    fun getAccountList(): LiveData<List<TransactionItemResponse>?> {
        val liveData = MutableLiveData<List<TransactionItemResponse>?>()

        api.getAccountTransactions().enqueue(object : Callback<List<TransactionItemResponse>?> {
            override fun onResponse(
                call: Call<List<TransactionItemResponse>?>,
                response: Response<List<TransactionItemResponse>?>
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<TransactionItemResponse>?>, t: Throwable) {
                liveData.postValue(null)
            }

        })

        return liveData
    }

}