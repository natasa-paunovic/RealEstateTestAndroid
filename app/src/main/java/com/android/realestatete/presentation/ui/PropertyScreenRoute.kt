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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.realestatete.presentation.ui.components.PropertyList
import com.android.realestatete.presentation.viewmodel.PropertyViewModel
import com.android.realestatete.presentation.ui.theme.AccentTeal
import com.android.realestatete.presentation.ui.theme.BackgroundColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun PropertyScreenRoute(viewModel: PropertyViewModel = koinViewModel()) {

    val properties = viewModel.properties.collectAsLazyPagingItems()
    val favouriteProperties by viewModel
        .observeFavouriteProperties()
        .collectAsState(initial = emptySet())

    PropertyScreen(
        properties = properties,
        favouriteProperties = favouriteProperties,
        onRetry = { properties.retry() },
        onToggleLike = { viewModel.toggleLike(it) }
    )
}

