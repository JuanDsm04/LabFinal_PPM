package com.uvg.ppm_labfinal.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetsListDTO(
    val results: List<AssetDTO>
)