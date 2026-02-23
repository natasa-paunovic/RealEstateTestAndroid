package com.android.realestatete.data.networking.dto

data class PropertiesResponseDto(
    val from: Int,
    val size: Int,
    val total: Int,
    val results: List<PropertyDto>,
    val maxFrom: Int?
)