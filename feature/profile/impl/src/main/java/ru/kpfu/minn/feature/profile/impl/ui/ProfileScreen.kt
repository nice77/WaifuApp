package ru.kpfu.minn.feature.profile.impl.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.feature.profile.impl.di.ProfileDependencies
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileAction
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileIntent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userId: String? = null,
) {
    val dependencies = (LocalContext.current as DependenciesContainer)
        .getDependencies(ProfileDependencies::class.java)
    val viewModel = viewModel {
        ProfileViewModel(
            dependencies = dependencies,
            userId = userId,
        )
    }
    val state by viewModel.stateFlow.collectAsState()
    val images = viewModel.imagesFlow.collectAsLazyPagingItems()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.actionFlow.collect { action ->
            when(action) {
                is ProfileAction.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = action.message)
                }
            }
        }
    }
    ObserveState(
        modifier = modifier,
        state = state,
        images = images,
        onImageClicked = { viewModel.handleIntent(ProfileIntent.OnImageClicked(it)) },
        onDismissDialogClicked = { viewModel.handleIntent(ProfileIntent.OnDismissDialogClicked) },
        onLikeClicked = { viewModel.handleIntent(ProfileIntent.OnImageLikeButtonClicked) },
        onSetAsWallpaperClicked = { viewModel.handleIntent(ProfileIntent.OnSetAsWallpaperClicked) },
        onRefresh = { viewModel.handleIntent(ProfileIntent.OnRefresh) },
    )
    SnackbarHost(hostState = snackbarHostState)
}
