package com.example.desafioaula2ioasys.activity

import android.app.Application
import com.example.desafioaula2ioasys.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                presentationModule
            ).androidContext(applicationContext)
        }
    }

}