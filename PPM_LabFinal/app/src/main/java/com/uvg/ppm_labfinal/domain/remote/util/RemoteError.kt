package com.uvg.ppm_labfinal.domain.remote.util

interface Error

enum class DataError: Error {
    NO_INTERNET,
    GENERIC_FAILURE
}