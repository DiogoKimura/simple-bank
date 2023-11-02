package com.example.sdk.data

import com.example.sdk.data.api.ApiInterface.Companion.BASE_URL
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val moshi: Moshi = Moshi.Builder().build()