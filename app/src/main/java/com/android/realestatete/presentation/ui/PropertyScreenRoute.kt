package com.android.realestatete.presentation.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.realestatete.presentation.viewmodel.PropertyViewModel
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

