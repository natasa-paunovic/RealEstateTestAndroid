package com.android.realestatete.data.networking.dto

data class PropertyDto(
    val id: String,
    val remoteViewing: Boolean?,
    val listingType: ListingTypeDto?,
    val listerBranding: ListerBrandingDto?,
    val listing: ListingDto?,
    val lister: ListerDto?
)