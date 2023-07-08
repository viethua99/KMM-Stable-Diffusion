package com.vproject.brushai.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val brushAiDispatchers: BrushAiDispatchers)

enum class BrushAiDispatchers {
    Default,
    IO,
}
