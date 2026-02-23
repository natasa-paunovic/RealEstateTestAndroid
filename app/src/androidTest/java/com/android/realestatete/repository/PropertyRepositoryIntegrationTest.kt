package com.android.realestatete.repository

import androidx.paging.PagingData
import com.android.realestatete.data.networking.api.ApiService
import com.android.realestatete.data.repository.PropertyRepositoryImpl
import com.android.realestatete.domain.model.Property
import com.android.realestatete.domain.repository.PropertyRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PropertyRepositoryIntegrationTest {

    private lateinit var server: MockWebServer
    private lateinit var api: ApiService
    private lateinit var repository: PropertyRepository

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)

        repository = PropertyRepositoryImpl(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun repositoryReturnsDataFromServer() = runTest {

        val jsonResponse = """
    {
      "from":0,
      "size":1,
      "total":1,
      "results":[]
    }
    """

        server.enqueue(
            MockResponse()
                .setBody(jsonResponse)
                .setResponseCode(200)
        )

        val result = repository.getProperties().first()

        assertTrue(result is PagingData<Property>)
    }
}