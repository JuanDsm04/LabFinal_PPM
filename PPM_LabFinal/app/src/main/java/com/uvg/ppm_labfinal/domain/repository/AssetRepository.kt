package com.uvg.ppm_labfinal.domain.repository

import com.uvg.ppm_labfinal.data.model.Asset
import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.Result

interface AssetRepository {
    suspend fun getAllAssets(): Result<List<Asset>, DataError>
    suspend fun getAssetByID(id: String): Asset
}