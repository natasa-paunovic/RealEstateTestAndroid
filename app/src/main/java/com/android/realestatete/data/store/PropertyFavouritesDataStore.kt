package com.android.realestatete.data.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.android.realestatete.domain.preference.PropertyFavouritesPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.emptySet

class PropertyFavouritesDataStore(
    private val context: Context
) : PropertyFavouritesPreference {

    companion object Companion {
        private val Context.dataStore by preferencesDataStore(
            name = "property_datastore"
        )
    }

    private val LIKE_KEY = stringSetPreferencesKey("liked_ids")


    override fun observeFavouriteProperties(): Flow<Set<String>> =
        context.dataStore.data.map { prefs ->
            prefs[LIKE_KEY] ?: emptySet()
        }

    override suspend fun toggleLikedProperty(id: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[LIKE_KEY] ?: emptySet()
            prefs[LIKE_KEY] = if (current.contains(id)) current - id else current + id
        }
    }
}

