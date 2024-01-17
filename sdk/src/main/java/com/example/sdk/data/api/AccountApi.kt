package com.example.sdk.data.api

import com.example.sdk.data.model.TransactionListResponse
import retrofit2.Call
import retrofit2.http.GET

interface AccountApi {
    @GET("transactions")
    fun getAccountTransactions(): Call<TransactionListResponse>

}