package com.uvg.ppm_labfinal.domain.remote

import com.uvg.ppm_labfinal.data.remote.dto.AssetsListDTO
import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.Result

interface CoinCapAPI {
    suspend fun getAllAssets(): Result<AssetsListDTO, DataError>
}