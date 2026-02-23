package com.android.realestatete.data.networking.dto

data class ListerBrandingDto(
    val logoUrl: String?,
    val legalName: String?,
    val name: String?,
    val address: BrandingAddressDto?,
    val adActive: Boolean?,
    val isQualityPartner: Boolean?,
    val isPremiumBranding: Boolean?,
    val profilePageUrlKeyword: String?
)