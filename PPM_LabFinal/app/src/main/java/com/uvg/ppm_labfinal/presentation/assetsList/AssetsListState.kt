package com.uvg.ppm_labfinal.presentation.assetsList

import com.uvg.ppm_labfinal.data.model.Asset

data class AssetsListState(
    val data: List<Asset> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)
