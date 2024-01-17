package com.example.sdk.data.api

import com.example.sdk.data.model.SummaryItemResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SummaryApi {
    @GET("decode")
    fun decode(): Call<SummaryItemResponse>

    @POST("make_payment")
    fun makePayment(
        @Body request: String
    ): ResponseBody
}