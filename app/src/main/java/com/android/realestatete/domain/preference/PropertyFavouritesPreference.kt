package com.android.realestatete.domain.preference

import kotlinx.coroutines.flow.Flow

interface PropertyFavouritesPreference {
    fun observeFavouriteProperties(): Flow<Set<String>>
    suspend fun toggleLikedProperty(id: String)
}