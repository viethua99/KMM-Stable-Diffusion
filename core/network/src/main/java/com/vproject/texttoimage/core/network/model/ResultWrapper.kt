package com.vproject.texttoimage.core.network.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class ResultWrapper<out ResponseType> {
    data class Success<out ResponseType>(val data: ResponseType) :
        ResultWrapper<ResponseType>()

    data class Error<ResponseType>(val message: ErrorType) : ResultWrapper<ResponseType>()
}

enum class ErrorType {
    HTTP,
    IO, // IO
    TIMEOUT, // Socket
    UNKNOWN
}

suspend fun <ResponseType> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> ResponseType
): ResultWrapper<ResponseType> {
    return try {
        val response = withContext(dispatcher) { apiCall.invoke() }
        ResultWrapper.Success(response)
    } catch (exception: Exception) {
        when (exception) {
            is HttpException -> ResultWrapper.Error(ErrorType.HTTP)
            is SocketTimeoutException -> ResultWrapper.Error(ErrorType.TIMEOUT)
            is IOException -> ResultWrapper.Error(ErrorType.IO)
            else -> ResultWrapper.Error(ErrorType.UNKNOWN)
        }
    }
}