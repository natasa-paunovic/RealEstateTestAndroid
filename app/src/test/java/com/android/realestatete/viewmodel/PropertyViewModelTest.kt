package com.android.realestatete.viewmodel

import androidx.paging.PagingData
import com.android.realestatete.domain.model.Price
import com.android.realestatete.domain.model.Property
import com.android.realestatete.domain.preference.PropertyFavouritesPreference
import com.android.realestatete.domain.repository.PropertyRepository
import com.android.realestatete.presentation.viewmodel.PropertyViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PropertyViewModelTest {

    private val repository = mockk<PropertyRepository>()
    private val preferences = mockk<PropertyFavouritesPreference>(relaxed = true)

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewmodel exposes paging data`() = runTest {

        coEvery {
            repository.getProperties()
        } returns flowOf(PagingData.empty())

        val viewModel = PropertyViewModel(
            repository,
            preferences
        )

        dispatcher.scheduler.advanceUntilIdle()

        assertNotNull(viewModel.properties)
    }

    @Test
    fun `toggleLike calls preference`() = runTest {

            coEvery {
                repository.getProperties()
            } returns flowOf(PagingData.empty())

            val viewModel = PropertyViewModel(
                repository,
                preferences
            )

            val property = Property(
                id = "1",
                title = "Test",
                imageUrl = "",
                price = Price(0, ""),
                address = ""
            )

            viewModel.toggleLike(property)

            advanceUntilIdle()

            coVerify(exactly = 1) {
                preferences.toggleLikedProperty("1")
            }

    }

}