package com.uvg.ppm_labfinal.domain.remote.util

import com.uvg.ppm_labfinal.domain.remote.util.Error as UtilError

sealed interface Result<out D, out E : UtilError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : UtilError>(val error: E) : Result<Nothing, E>
}

inline fun <T, E : UtilError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

inline fun <T, E : UtilError> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E : UtilError> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}