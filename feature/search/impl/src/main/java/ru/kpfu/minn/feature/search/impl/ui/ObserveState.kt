package ru.kpfu.minn.feature.search.impl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import ru.kpfu.minn.core.common.ui.FullscreenImageDialog
import ru.kpfu.minn.core.common.ui.ShimmerAnimation
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.search.impl.R
import ru.kpfu.minn.feature.search.impl.ui.composables.ExpandableMenu
import ru.kpfu.minn.feature.search.impl.ui.model.SearchUiModel
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchState

@Composable
fun ObserveState(
    modifier: Modifier = Modifier,
    state: SearchState,
    stringResProvider: StringResProvider,
    onTextChanged: (String) -> Unit,
    onFilterClicked: () -> Unit,
    onApplyFilterClicked: () -> Unit,
    onSafeSearchChecked: (Boolean) -> Unit,
    onSearchClicked: () -> Unit,
    onTagClicked: (TagUiModel) -> Unit,
    onSwitchClicked: (Boolean) -> Unit,
    searchResults: LazyPagingItems<SearchUiModel>,
    onSearchItemClicked: (SearchUiModel) -> Unit,
    onDismissDialogClicked: () -> Unit,
    onLikeClicked: () -> Unit,
    onSetAsWallpaperClicked: () -> Unit,
) {
    Box (
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(count = 2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ){
            item(
                span = StaggeredGridItemSpan.FullLine
            ) {
                Column {
                    OutlinedTextField(
                        value = state.searchQuery,
                        onValueChange = onTextChanged,
                        label = { Text(stringResProvider.getString(R.string.search)) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        enabled = !state.isImageSearch,
                        trailingIcon = {
                            Row {
                                IconButton(
                                    onClick = onFilterClicked,
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.FilterAlt,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                    )
                                }
                                IconButton(
                                    onClick = onSearchClicked,
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                    )
                                }
                            }
                        }
                    )
                    ExpandableMenu(
                        state = state,
                        stringResProvider = stringResProvider,
                        onSafeSearchChecked = onSafeSearchChecked,
                        onApplyFilterClicked = onApplyFilterClicked,
                        onTagClicked = onTagClicked,
                        onSwitchClicked = onSwitchClicked,
                    )
                }
            }
            items(searchResults.itemCount) { item ->
                searchResults[item]?.let { searchUiModel ->
                    val model = when (searchUiModel) {
                        is SearchUiModel.ImageUiModel -> searchUiModel.previewUrl
                        is SearchUiModel.UserUiModel -> searchUiModel.imageUrl
                    }
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onSearchItemClicked(searchUiModel) },
                        verticalArrangement = Arrangement.Center,
                    ) {
                        SubcomposeAsyncImage(
                            model = model,
                            contentDescription = null,
//                            modifier = Modifier
//                                .padding(8.dp)
//                                .clip(RoundedCornerShape(8.dp))
//                                .clickable { onSearchItemClicked(searchUiModel) },
                            loading = {
                                ShimmerAnimation(
                                    modifier = Modifier
                                        .heightIn(max = 512.dp)
                                        .fillMaxWidth()
                                )
                            }
                        )
                        if (!state.isImageSearch) {
                            Text(
                                text = (searchUiModel as SearchUiModel.UserUiModel).username,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                }
            }
        }
        if (state.isDialogShown) {
            FullscreenImageDialog(
                modifier = Modifier.align(Alignment.Center),
                imageUrl = state.clickedImage.imageUrl,
                isLiked = state.clickedImage.isLiked,
                onDismissDialogClicked = onDismissDialogClicked,
                onLikeClicked = onLikeClicked,
                onSetAsWallpaperClicked = onSetAsWallpaperClicked,
            )
        }
    }
}
