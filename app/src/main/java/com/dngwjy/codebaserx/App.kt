package com.dngwjy.codebaserx

import android.app.Application
import com.dngwjy.codebaserx.di.networkModule
import com.dngwjy.codebaserx.di.repositoryModule
import com.dngwjy.codebaserx.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, repositoryModule, viewModelModule))
    }
}