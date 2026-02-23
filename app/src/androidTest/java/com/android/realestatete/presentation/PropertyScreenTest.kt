package com.android.realestatete.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.realestatete.domain.model.Price
import com.android.realestatete.domain.model.Property
import com.android.realestatete.presentation.ui.PropertyScreen
import com.android.realestatete.presentation.ui.components.PropertyList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.realestatete.paging.MockErrorPagingSource
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf

@RunWith(AndroidJUnit4::class)
class PropertyScreenTest {

    object PropertyMockData {

        fun properties() = listOf(
            Property(
                id = "1",
                imageUrl = "https://picsum.photos/400/300?swiss1",
                title = "Modern Apartment Zurich Center",
                price = Price(220000, "CHF"),
                address = "Zurich"
            ),
            Property(
                id = "2",
                imageUrl = "https://picsum.photos/400/300?swiss2",
                title = "Luxury Lake View Apartment",
                price = Price(350000, "CHF"),
                address = "Geneva, Switzerland"
            ),
            Property(
                id = "3",
                imageUrl = "https://picsum.photos/400/300?swiss3",
                title = "Cozy Mountain Chalet",
                price = Price(180000, "CHF"),
                address = "Interlaken, Switzerland"
            ),
            Property(
                id = "4",
                imageUrl = "https://picsum.photos/400/300?swiss4",
                title = "Modern Studio Apartment",
                price = Price(150000, "CHF"),
                address = "Basel, Switzerland"
            ),
            Property(
                id = "5",
                imageUrl = "https://picsum.photos/400/300?swiss5",
                title = "Premium Penthouse",
                price = Price(500000, "CHF"),
                address = "Lausanne, Switzerland"
            )
        )
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun propertyScreen_showsList_whenLoaded() {
        composeTestRule.setContent {

            val pagingItems = flowOf(
                PagingData.from(
                    listOf(
                        Property(
                            id = "1",
                            imageUrl = "",
                            title = "Modern Apartment Zurich Center",
                            price = Price(220000, "CHF"),
                            address = "Zurich"
                        )
                    )
                )
            ).collectAsLazyPagingItems()

            PropertyList(
                properties = pagingItems,
                likedIds = emptySet(),
                onPropertyLike = {}
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText("Zurich")
            .assertExists()
    }



    @Test
    fun propertyScreen_clickLikeButton() {

            composeTestRule.setContent {

                val pagingItems = flowOf(
                    PagingData.from(PropertyMockData.properties())
                ).collectAsLazyPagingItems()

                PropertyList(
                    properties = pagingItems,
                    likedIds = emptySet(),
                    onPropertyLike = {}
                )
            }


        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithContentDescription("Like button")[0]
            .performClick()
    }

    @Test
    fun propertyScreen_retryOnErrorClick() {

        var retryClicked = false

        composeTestRule.setContent {

            val pagingItems = Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = { MockErrorPagingSource() }
            ).flow.collectAsLazyPagingItems()

            PropertyScreen(
                properties = pagingItems,
                favouriteProperties = emptySet(),
                onRetry = { retryClicked = true },
                onToggleLike = {}
            )
        }

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("error_text")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag("error_text")
            .assertExists()
            .performClick()

        assertTrue(retryClicked)
    }

}