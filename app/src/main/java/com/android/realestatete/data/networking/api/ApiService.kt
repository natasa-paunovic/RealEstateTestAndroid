package com.android.realestatete.data.networking.api

import com.android.realestatete.data.networking.dto.PropertiesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("properties")
    suspend fun getProperties(
        @Query("from") from: Int,
        @Query("size") size: Int
    ): PropertiesResponseDto

}