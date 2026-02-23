package com.android.realestatete.domain.model

import androidx.compose.runtime.Immutable


data class Property(
    val id: String,
    val imageUrl: String,
    val title:String,
    val price: Price?,
    val address: String
)
