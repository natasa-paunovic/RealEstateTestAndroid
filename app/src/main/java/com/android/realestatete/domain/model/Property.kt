package com.android.realestatete.domain.model

data class Property(
    val id: String,
    val imageUrl: String,
    val title:String,
    val price: Price?,
    val address: String
)
