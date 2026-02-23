package com.android.realestatete.data.networking.dto

data class ListingDto(
    val id: String?,
    val offerType: String?,
    val categories: List<String>?,
    val prices: PricesDto?,
    val address: AddressDto?,
    val characteristics: CharacteristicsDto?,
    val localization: LocalizationDto?
)