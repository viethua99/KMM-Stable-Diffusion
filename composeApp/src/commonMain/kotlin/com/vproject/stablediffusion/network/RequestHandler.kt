package com.vproject.stablediffusion.network

import com.vproject.stablediffusion.stableDiffusionDispatchers
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

suspend inline fun <reified T> requestHandler(
    crossinline response: suspend () -> HttpResponse
): T = withContext(stableDiffusionDispatchers.io) {

    val result = try {
        response()
    } catch(e: IOException) {
        throw StableDiffusionException(StableDiffusionError.ServiceUnavailable, "")
    }

    when(result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> {
            throw StableDiffusionException(StableDiffusionError.ClientError, result.body<String>().toString())
        }
        500 -> throw StableDiffusionException(StableDiffusionError.ServerError, "")
        else -> throw StableDiffusionException(StableDiffusionError.UnknownError, "")
    }

    return@withContext try {
        result.body()
    } catch(e: Exception) {
        throw StableDiffusionException(StableDiffusionError.ServerError, "")
    }
}
enum class StableDiffusionError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class StableDiffusionException(error: StableDiffusionError, message: String): Exception(
    "Something goes wrong: $error $message"
)