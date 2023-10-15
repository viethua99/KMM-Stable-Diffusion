package com.vproject.texttoimage.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

internal class TimeoutInterceptor : Interceptor {
    private companion object {
        private const val DEFAULT_CONNECT_TIMEOUT_MS = 120000
        private const val DEFAULT_READ_TIMEOUT_MS = 120000
        private const val DEFAULT_WRITE_TIMEOUT_MS = 120000
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val connectTimeoutRequest = request.header("CONNECT_TIMEOUT")
        val readTimeoutRequest = request.header("READ_TIMEOUT")
        val writeTimeoutRequest = request.header("WRITE_TIMEOUT")

        return chain
            .withConnectTimeout(if (connectTimeoutRequest.isNullOrEmpty()) DEFAULT_CONNECT_TIMEOUT_MS else Integer.valueOf(connectTimeoutRequest), TimeUnit.MILLISECONDS)
            .withReadTimeout(if (readTimeoutRequest.isNullOrEmpty()) DEFAULT_READ_TIMEOUT_MS else Integer.valueOf(readTimeoutRequest), TimeUnit.MILLISECONDS)
            .withWriteTimeout(if (writeTimeoutRequest.isNullOrEmpty()) DEFAULT_WRITE_TIMEOUT_MS else Integer.valueOf(writeTimeoutRequest), TimeUnit.MILLISECONDS)
            .proceed(request)
    }
}