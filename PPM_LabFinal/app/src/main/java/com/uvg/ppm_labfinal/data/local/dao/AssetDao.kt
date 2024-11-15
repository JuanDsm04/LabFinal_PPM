package com.uvg.ppm_labfinal.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uvg.ppm_labfinal.data.local.entity.AssetEntity

@Dao
interface AssetDao {
    @Upsert
    suspend fun insertAll(assets: List<AssetEntity>)

    @Query("SELECT * FROM assets")
    suspend fun getAllAssets(): List<AssetEntity>

    @Query("SELECT * FROM assets WHERE id = :id")
    suspend fun getAsset(id: String): AssetEntity
}
