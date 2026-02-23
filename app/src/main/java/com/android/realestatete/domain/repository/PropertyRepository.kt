package com.android.realestatete.domain.repository

import androidx.paging.PagingData
import com.android.realestatete.domain.model.Property
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {
    fun getProperties(): Flow<PagingData<Property>>

}