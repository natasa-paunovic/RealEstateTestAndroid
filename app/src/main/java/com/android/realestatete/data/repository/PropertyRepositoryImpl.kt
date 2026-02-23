package com.android.realestatete.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.realestatete.data.networking.api.ApiService
import com.android.realestatete.data.networking.paging.PropertyPagingSource
import com.android.realestatete.domain.model.Property
import com.android.realestatete.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow

class PropertyRepositoryImpl(
    private val api: ApiService
) : PropertyRepository {


    override fun getProperties(): Flow<PagingData<Property>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,       // load next page when 5 items from end
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { PropertyPagingSource(api) }
        ).flow
    }
}