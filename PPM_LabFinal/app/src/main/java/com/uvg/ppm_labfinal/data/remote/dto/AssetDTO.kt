package com.uvg.ppm_labfinal.data.remote.dto

import com.uvg.ppm_labfinal.data.local.entity.AssetEntity
import kotlinx.serialization.Serializable
import com.uvg.ppm_labfinal.data.model.Asset

@Serializable
data class AssetDTO(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String
)

fun AssetDTO.mapToModel(): Asset {
    return Asset(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd.toDoubleOrNull() ?: 0.0,
        changePercent24Hr = changePercent24Hr
    )
}

fun AssetDTO.mapToEntity(): AssetEntity {
    return AssetEntity(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd.toDoubleOrNull() ?: 0.0,
        changePercent24Hr = changePercent24Hr
    )
}