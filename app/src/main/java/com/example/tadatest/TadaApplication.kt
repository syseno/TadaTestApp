package com.example.tadatest

import android.app.Application
import com.example.tadatest.networks.networkModule
import com.example.tadatest.repositories.repositoryModule
import com.example.tadatest.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TadaApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TadaApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}