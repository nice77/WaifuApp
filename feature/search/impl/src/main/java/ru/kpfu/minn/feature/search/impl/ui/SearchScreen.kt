package ru.kpfu.minn.feature.search.impl.ui

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
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.search.impl.di.SearchDependencies
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchAction
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchIntent

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    stringResProvider: StringResProvider,
    onUserClicked: (String) -> Unit,
) {
    val dependencies = (LocalContext.current as DependenciesContainer)
        .getDependencies(SearchDependencies::class.java)
    val viewModel = viewModel { SearchViewModel(dependencies) }
    val state by viewModel.stateFlow.collectAsState()
    val searchResults = viewModel.searchResultFlow.collectAsLazyPagingItems()

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.actionFlow.collect { action ->
            when(action) {
                is SearchAction.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = action.message)
                }
                is SearchAction.PerformNavigation -> {
                    onUserClicked(action.uid)
                }
            }
        }
    }

    ObserveState(
        state = state,
        modifier = modifier,
        searchResults = searchResults,
        stringResProvider = stringResProvider,
        onTextChanged = { viewModel.handleIntent(SearchIntent.OnTextChanged(it)) },
        onFilterClicked = { viewModel.handleIntent(SearchIntent.OnFilterClicked) },
        onApplyFilterClicked = { viewModel.handleIntent(SearchIntent.OnApplyFilterClicked) },
        onSafeSearchChecked = { viewModel.handleIntent(SearchIntent.OnSafeSearchChecked(it)) },
        onSearchClicked = { viewModel.handleIntent(SearchIntent.OnSearchClicked) },
        onTagClicked = { viewModel.handleIntent(SearchIntent.OnTagClicked(it)) },
        onSwitchClicked = { viewModel.handleIntent(SearchIntent.OnSwitchClicked(it)) },
        onSearchItemClicked = { viewModel.handleIntent(SearchIntent.OnSearchItemClicked(it)) },
        onDismissDialogClicked = { viewModel.handleIntent(SearchIntent.OnDismissDialogClicked) },
        onLikeClicked = { viewModel.handleIntent(SearchIntent.OnLikeClicked) },
        onSetAsWallpaperClicked = { viewModel.handleIntent(SearchIntent.OnSetAsWallpaperClicked) },
    )
    SnackbarHost(hostState = snackbarHostState)
}