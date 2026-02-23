package com.android.realestatete.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.android.realestatete.R
import com.android.realestatete.domain.model.Property
import com.android.realestatete.presentation.ui.theme.AccentTeal

@Composable
fun PropertyList(
    properties: LazyPagingItems<Property>,
    likedIds: Set<String>,
    onPropertyLike: (Property) -> Unit
) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = properties.itemCount,
            key = properties.itemKey { it.id }
        ) { index ->
            val property = properties[index] ?: return@items

            val isLiked = remember(likedIds, property.id) {
                property.id in likedIds
            }

            PropertyItem(
                property = property,
                isLiked = isLiked,
                onPropertyLike = { onPropertyLike(property) }
            )
        }

        item {
            when (val appendState = properties.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(
                        color = AccentTeal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                is LoadState.Error -> {
                    Text(
                        text = appendState.error.message ?: stringResource(R.string.load_more_failed_tap_to_retry),
                        color = Color(0xFFE8445A),
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { properties.retry() }
                    )
                }
                else -> {}
            }
        }
    }
}