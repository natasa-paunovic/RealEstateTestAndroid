package com.android.realestatete.presentation.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.android.realestatete.domain.model.Property
import com.android.realestatete.presentation.ui.components.PropertyList
import com.android.realestatete.presentation.ui.theme.AccentTeal
import com.android.realestatete.presentation.ui.theme.BackgroundColor


@Composable
fun PropertyScreen(
    properties: LazyPagingItems<Property>,
    favouriteProperties: Set<String>,
    onRetry: () -> Unit,
    onToggleLike: (Property) -> Unit
) {



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        when (properties.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    color = AccentTeal,
                    modifier = Modifier
                        .testTag("loading_indicator")
                        .align(Alignment.Center)
                )
            }
            is LoadState.Error -> {
                val error = (properties.loadState.refresh as LoadState.Error).error
                Text(
                    text = error.message ?: "Unknown error",
                    color = Color(0xFFE8445A),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .testTag("error_text")
                        .align(Alignment.Center)
                        .padding(24.dp)
                        .clickable { onRetry() }
                )
            }
            else -> {
                PropertyList(
                    properties = properties,
                    likedIds = favouriteProperties,
                    onPropertyLike = { onToggleLike(it) }
                )
            }
        }
    }
}

