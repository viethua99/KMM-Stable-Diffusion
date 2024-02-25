package com.vproject.stablediffusion.di

import com.vproject.stablediffusion.util.TestUtil
import org.koin.dsl.module

actual fun platformModule() = module {
 single { TestUtil() }
}