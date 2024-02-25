package com.vproject.stablediffusion

import kotlinx.coroutines.CoroutineDispatcher

interface StableDiffusionDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

expect val stableDiffusionDispatchers: StableDiffusionDispatchers
