package com.android.realestatete.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.realestatete.domain.model.Property
import com.android.realestatete.domain.preference.PropertyFavouritesPreference
import com.android.realestatete.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PropertyViewModel(
    private val repository: PropertyRepository,
    private val propertyFavouritesPreference: PropertyFavouritesPreference
) : ViewModel() {

  val properties: StateFlow<PagingData<Property>> =
      repository.getProperties()
          .cachedIn(viewModelScope)   // survives config changes, no re-fetch on rotation
          .stateIn(
              scope = viewModelScope,
              started = SharingStarted.Eagerly,
              initialValue = PagingData.empty()
          )


    fun toggleLike(property: Property) {
        viewModelScope.launch {
            propertyFavouritesPreference.toggleLikedProperty(property.id)
        }
    }

    fun observeFavouriteProperties(): Flow<Set<String>> {
        return propertyFavouritesPreference.observeFavouriteProperties()
    }
}