package com.uvg.ppm_labfinal.data.model

data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent24Hr: String
)