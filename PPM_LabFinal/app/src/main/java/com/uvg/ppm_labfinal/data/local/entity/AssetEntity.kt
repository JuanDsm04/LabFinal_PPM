package com.uvg.ppm_labfinal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.ppm_labfinal.data.model.Asset

@Entity(tableName = "assets")
data class AssetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent24Hr: String
)

fun AssetEntity.mapToModel(): Asset {
    return Asset (
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}