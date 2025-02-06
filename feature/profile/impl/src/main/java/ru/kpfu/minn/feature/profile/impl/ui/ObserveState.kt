package ru.kpfu.minn.feature.profile.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import ru.kpfu.minn.core.common.ui.FullscreenImageDialog
//import ru.kpfu.minn.feature.profile.impl.ui.composables.FullscreenImageDialog
import ru.kpfu.minn.feature.profile.impl.ui.composables.ProfileList
import ru.kpfu.minn.feature.profile.impl.ui.model.ImageUiModel
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ObserveState(
    modifier: Modifier = Modifier,
    state: ProfileState,
    images: LazyPagingItems<ImageUiModel>,
    onImageClicked: (ImageUiModel) -> Unit,
    onDismissDialogClicked: () -> Unit,
    onLikeClicked: () -> Unit,
    onSetAsWallpaperClicked: () -> Unit,
    onRefresh: () -> Unit,
) {
    val refreshingState = rememberPullRefreshState(state.refreshing.value, onRefresh)
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(refreshingState, enabled = true),
    ) {
        ProfileList(
            modifier = Modifier
                .blur(if (state.isDialogShown) 16.dp else 0.dp),
            state = state,
            images = images,
            onImageClicked = onImageClicked,
        )
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
        PullRefreshIndicator(
            state.refreshing.value,
            refreshingState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}
