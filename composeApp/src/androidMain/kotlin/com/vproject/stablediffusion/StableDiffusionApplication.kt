package com.vproject.stablediffusion

import android.app.Application
import com.vproject.stablediffusion.di.initKoin
import org.koin.android.ext.koin.androidContext

class StableDiffusionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}
