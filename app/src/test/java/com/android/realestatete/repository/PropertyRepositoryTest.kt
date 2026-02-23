package com.android.realestatete.repository


import com.android.realestatete.data.networking.api.ApiService
import com.android.realestatete.data.networking.dto.PropertiesResponseDto
import com.android.realestatete.data.repository.PropertyRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PropertyRepositoryTest {

    private val api = mockk<ApiService>()
    private val repository = PropertyRepositoryImpl(api)

    @Test
    fun `getProperties returns paging data`() = runTest {

        coEvery {
            api.getProperties(any(), any())
        } returns PropertiesResponseDto(
            from = 0,
            size = 10,
            total = 0,
            results = emptyList(),
            maxFrom = 0
        )

        val result = repository.getProperties()

        assertNotNull(result)
    }
}