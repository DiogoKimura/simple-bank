package com.example.simplebank

import android.app.Application
import com.example.sdk.di.module.apiModule
import com.example.sdk.di.module.repoModule
import com.example.sdk.di.module.sdkModule
import com.example.simplebank.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(listOf(sdkModule, apiModule, repoModule, viewModelModule))
        }

    }
}