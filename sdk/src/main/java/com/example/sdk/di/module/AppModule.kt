package com.example.sdk.di.module

import com.example.sdk.data.AuthInterceptor
import com.example.sdk.data.api.AccountApi
import com.example.sdk.data.api.SummaryApi
import com.example.sdk.data.moshi
import com.example.sdk.data.provideOkHttpClient
import com.example.sdk.data.provideRetrofitClient
import com.example.sdk.data.repo.AccountRepository
import com.example.sdk.data.repo.SummaryRepository
import com.example.sdk.data.retrofit
import org.koin.dsl.module
import retrofit2.Retrofit

val sdkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofitClient(get()) }
}

val apiModule = module {
    single { get<Retrofit>().create(AccountApi::class.java)}
    single { get<Retrofit>().create(SummaryApi::class.java)}
}

val repoModule = module {
    single { AccountRepository(get()) }
    single { SummaryRepository(get()) }
}