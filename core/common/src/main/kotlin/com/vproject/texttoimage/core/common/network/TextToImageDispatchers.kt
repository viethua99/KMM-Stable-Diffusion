package com.vproject.texttoimage.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val textToImageDispatchers: TextToImageDispatchers)

enum class TextToImageDispatchers {
    Default,
    IO,
}
