package com.android.realestatete.data.networking.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.realestatete.data.mapper.toDomain
import com.android.realestatete.data.networking.api.ApiService
import com.android.realestatete.domain.model.Property

class PropertyPagingSource(
    private val api: ApiService
) : PagingSource<Int, Property>() {

    override fun getRefreshKey(state: PagingState<Int, Property>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
        return try {
            val from = params.key ?: 0
            val response = api.getProperties(from, params.loadSize)

            val nextKey = if (from + response.results.size >= response.total) {
                null
            } else {
                from + response.results.size
            }

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (from == 0) null else from,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}