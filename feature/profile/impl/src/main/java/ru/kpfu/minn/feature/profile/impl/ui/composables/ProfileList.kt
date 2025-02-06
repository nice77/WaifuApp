package ru.kpfu.minn.feature.profile.impl.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import ru.kpfu.minn.core.common.ui.ShimmerAnimation
import ru.kpfu.minn.feature.profile.impl.ui.model.ImageUiModel
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileState

@Composable
fun ProfileList(
    modifier: Modifier = Modifier,
    state: ProfileState,
    images: LazyPagingItems<ImageUiModel>,
    onImageClicked: (ImageUiModel) -> Unit,
) {
    LazyVerticalStaggeredGrid (
        columns = StaggeredGridCells.Fixed(count = 2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .graphicsLayer { alpha = if (state.isDialogShown) 0.5f else 1f },
    ) {
        item(span = StaggeredGridItemSpan.FullLine) { UserCard(state) }
        items(images.itemCount) { item ->
            SubcomposeAsyncImage(
                model = images[item]?.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { images[item]?.let { onImageClicked(it) } },
                loading = {
                    ShimmerAnimation(
                        modifier = Modifier
                            .heightIn(max = 128.dp)
                            .fillMaxWidth()
                    )
                }
            )
        }
    }
}
