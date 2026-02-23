package com.android.realestatete.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.realestatete.domain.model.Property

class MockErrorPagingSource : PagingSource<Int, Property>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
        return LoadResult.Error(Throwable("Test error"))
    }

    override fun getRefreshKey(state: PagingState<Int, Property>): Int? = null
}