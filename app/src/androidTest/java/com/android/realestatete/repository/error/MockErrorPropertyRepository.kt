package com.android.realestatete.repository.error

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.realestatete.domain.model.Property
import com.android.realestatete.domain.repository.PropertyRepository
import com.android.realestatete.paging.MockErrorPagingSource
import kotlinx.coroutines.flow.Flow

class MockErrorPropertyRepository : PropertyRepository {

        override fun getProperties(): Flow<PagingData<Property>> {
            return Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { MockErrorPagingSource() }
            ).flow
        }
    }