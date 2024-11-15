package com.uvg.ppm_labfinal.data.local.repository

import com.uvg.ppm_labfinal.data.local.dao.AssetDao
import com.uvg.ppm_labfinal.data.model.Asset
import com.uvg.ppm_labfinal.data.remote.KtorCoinCapAPI
import com.uvg.ppm_labfinal.data.remote.dto.mapToEntity
import com.uvg.ppm_labfinal.data.remote.dto.mapToModel
import com.uvg.ppm_labfinal.data.local.entity.mapToModel
import com.uvg.ppm_labfinal.domain.remote.util.DataError
import com.uvg.ppm_labfinal.domain.remote.util.Result
import com.uvg.ppm_labfinal.domain.repository.AssetRepository

class LocalAssetRepository(
    private val assetDao: AssetDao,
    private val coinCapAPI: KtorCoinCapAPI
) : AssetRepository {
    override suspend fun getAllAssets(): Result<List<Asset>, DataError> {
        when (val result = coinCapAPI.getAllAssets()) {
            is Result.Error -> {
                val localAssets = assetDao.getAllAssets()

                return if (localAssets.isEmpty()) {
                    if (result.error == DataError.NO_INTERNET) {
                        Result.Error(DataError.NO_INTERNET)
                    } else {
                        Result.Error(DataError.GENERIC_FAILURE)
                    }
                } else {
                    Result.Success(localAssets.map { it.mapToModel() })
                }
            }

            is Result.Success -> {
                val remoteAssets = result.data.results

                assetDao.insertAll(
                    remoteAssets.map { it.mapToEntity() }
                )
                return Result.Success(remoteAssets.map { it.mapToModel() })
            }
        }
    }

    override suspend fun getAssetByID(id: String): Asset {
        return assetDao.getAsset(id).mapToModel()
    }
}
