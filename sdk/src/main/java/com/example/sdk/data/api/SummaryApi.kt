package com.example.sdk.data.api

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SummaryApi {
    @GET("decode")
    fun decode(): ResponseBody

    @POST("make_payment")
    fun makePayment(
        @Body request: String
    ) : ResponseBody
}