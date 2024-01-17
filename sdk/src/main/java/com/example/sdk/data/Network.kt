package com.example.sdk.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "https://my-json-server.typicode.com/diogokimura/simplebankmock/"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val moshi: Moshi = Moshi.Builder().build()

fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().apply {
        addInterceptor(authInterceptor)
        addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
    }.build()