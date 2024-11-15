package com.uvg.ppm_labfinal.data.remote.util

import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.client.call.body

suspend inline fun <reified T> responseToResult(
    response: HttpResponse

): Result<T, DataError> {
    return when(response.status.value) {

        in 200..299 -> {
            try {
                Result.Success(response.body<T>())

            } catch(e: Exception) {
                Result.Error(DataError.GENERIC_FAILURE)

            }
        }
        else -> Result.Error(DataError.GENERIC_FAILURE)
    }
}