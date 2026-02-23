package com.android.realestatete.data.networking.dto

data class AddressDto(
    val country: String?,
    val locality: String?,
    val postalCode: String?,
    val region: String?,
    val street: String?,
    val geoCoordinates: GeoCoordinatesDto?
)