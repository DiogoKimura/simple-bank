package com.example.sdk.di.module

import com.example.sdk.data.api.AccountApi
import com.example.sdk.data.api.SummaryApi
import com.example.sdk.data.moshi
import com.example.sdk.data.repo.AccountRepository
import com.example.sdk.data.repo.SummaryRepository
import com.example.sdk.data.retrofit
import org.koin.dsl.module
import retrofit2.Retrofit

val sdkModule = module {
    listOf(retrofit, moshi)
}

val apiModule = module {
    factory { get<Retrofit>().create(AccountApi::class.java)}
    factory { get<Retrofit>().create(SummaryApi::class.java)}
}

val repoModule = module {
    single { AccountRepository() }
    single { SummaryRepository() }
}