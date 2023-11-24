package com.vproject.stablediffusion

import android.app.Application
import com.vproject.stablediffusion.di.initKoin

class StableDiffusionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
