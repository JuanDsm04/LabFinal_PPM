package com.uvg.ppm_labfinal.data.remote

import com.uvg.ppm_labfinal.data.remote.dto.AssetsListDTO
import com.uvg.ppm_labfinal.data.remote.util.safeCall
import com.uvg.ppm_labfinal.domain.remote.CoinCapAPI
import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCoinCapAPI(
    private val httpClient: HttpClient
) : CoinCapAPI {
    override suspend fun getAllAssets(): Result<AssetsListDTO, DataError> {
        return safeCall<AssetsListDTO> {
            httpClient.get("https://api.coincap.io/v2/assets")
        }
    }
}
