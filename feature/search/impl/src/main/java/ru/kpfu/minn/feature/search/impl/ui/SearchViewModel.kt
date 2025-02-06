package ru.kpfu.minn.feature.search.impl.ui

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.kpfu.minn.core.common.BaseViewModel
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.api.usecase.FetchTagsUseCase
import ru.kpfu.minn.feature.search.api.usecase.GetIsImageFavoriteUseCase
import ru.kpfu.minn.feature.search.api.usecase.ManageImageFavoritnessUseCase
import ru.kpfu.minn.feature.search.api.usecase.SetAsWallpaperUseCase
import ru.kpfu.minn.feature.search.impl.di.DaggerSearchComponent
import ru.kpfu.minn.feature.search.impl.di.SearchDependencies
import ru.kpfu.minn.feature.search.impl.repository.paging.SearchPagingSource
import ru.kpfu.minn.feature.search.impl.repository.paging.UserPagingSource
import ru.kpfu.minn.feature.search.impl.ui.model.SearchUiModel
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchAction
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchIntent
import ru.kpfu.minn.feature.search.impl.ui.mvi.SearchState
import ru.kpfu.minn.feature.search.impl.utils.toDomainModel
import ru.kpfu.minn.feature.search.impl.utils.toUiModel
import javax.inject.Inject

class SearchViewModel(
    dependencies: SearchDependencies
): BaseViewModel<SearchAction, SearchState, SearchIntent>(
    initialState = SearchState()
) {
    private val component = DaggerSearchComponent
        .factory()
        .create(dependencies)
        .also { it.inject(this@SearchViewModel) }

    @Inject
    lateinit var fetchTagsUseCase: FetchTagsUseCase

    @Inject
    lateinit var manageImageFavoritnessUseCase: ManageImageFavoritnessUseCase

    @Inject
    lateinit var getIsImageFavoriteUseCase: GetIsImageFavoriteUseCase

    @Inject
    lateinit var setAsWallpaperUseCase: SetAsWallpaperUseCase

    private var searchPagingSource: SearchPagingSource? = null
    private var userPagingSource: UserPagingSource? = null

    val searchResultFlow = createSearchPager()
        .flow
        .map { pagingData ->
            pagingData.map { item ->
                when (item) {
                    is ImageDomainModel -> {
                        var result: SearchUiModel.ImageUiModel = SearchUiModel.ImageUiModel("", "")
                        getIsImageFavoriteUseCase(item).onSuccess {
                             result = item.toUiModel(it)
                        }.onFailure { e ->
                            _actionFlow.emit(SearchAction.ShowMessage(e.message ?: "Unknown error"))
                        }
                        result
                    }
                    is UserDomainModel -> item.toUiModel()
                    else -> throw NoSuchElementException()
                }
            }
        }
        .cachedIn(viewModelScope)

    override fun handleIntent(intent: SearchIntent) {
        when (intent) {
            SearchIntent.OnApplyFilterClicked -> onApplyFilterClicked()
            SearchIntent.OnFilterClicked -> handleOnFilterClicked()
            is SearchIntent.OnTextChanged -> {
                _stateFlow.value = _stateFlow.value.copy(
                    searchQuery = intent.text
                )
            }
            is SearchIntent.OnSafeSearchChecked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isSafeSearchEnabled = intent.isChecked
                )
            }
            SearchIntent.OnSearchClicked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isExpanded = false,
                )
                userPagingSource?.invalidate()
            }
            is SearchIntent.OnTagClicked -> {
                val index = _stateFlow.value.tagsList.indexOf(intent.tagUiModel)
                val selected = _stateFlow.value.tagsList[index].selected
                _stateFlow.value.tagsList[index] = _stateFlow.value.tagsList[index].copy(
                    selected = !selected
                )
            }
            is SearchIntent.OnSwitchClicked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isImageSearch = intent.isChecked,
                )
                searchPagingSource?.invalidate()
                userPagingSource?.invalidate()
            }
            is SearchIntent.OnSearchItemClicked -> onSearchItemClicked(intent)
            SearchIntent.OnDismissDialogClicked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isDialogShown = false,
                )
            }
            SearchIntent.OnLikeClicked -> onLikeClicked()
            SearchIntent.OnSetAsWallpaperClicked -> onSetAsWallpaperClicked()
        }
    }

    private fun onSearchItemClicked(intent: SearchIntent.OnSearchItemClicked) {
        when (intent.searchUiModel) {
            is SearchUiModel.ImageUiModel -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isDialogShown = true,
                    clickedImage = intent.searchUiModel,
                )
            }
            is SearchUiModel.UserUiModel -> {
                viewModelScope.launch {
                    _actionFlow.emit(SearchAction.PerformNavigation(intent.searchUiModel.uid))
                }
            }
        }
    }

    private fun createSearchPager(): Pager<Long, out Any> {
        return Pager(
            PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
            )
        ) {
            if (_stateFlow.value.isImageSearch) {
                val tags = _stateFlow.value.tagsList.filter {
                    it.selected
                }.map(TagUiModel::toDomainModel)
                searchPagingSource = component.searchPagingSourceFactory().create(tags)
                searchPagingSource!!
            } else {
                userPagingSource =
                    component.userPagingSourceFactory().create(_stateFlow.value.searchQuery)
                userPagingSource!!
            }
        }
    }

    private fun onSetAsWallpaperClicked() {
        viewModelScope.launch {
            setAsWallpaperUseCase(
                imageDomainModel = _stateFlow.value.clickedImage.toDomainModel()
            )
        }
    }

    private fun onLikeClicked() {
        viewModelScope.launch {
            manageImageFavoritnessUseCase(
                imageDomainModel = _stateFlow.value.clickedImage.toDomainModel(),
                toRemove = _stateFlow.value.clickedImage.isLiked.value
            )
        }
    }

    private fun onApplyFilterClicked() {
        _stateFlow.value = _stateFlow.value.copy(
            isExpanded = false,
        )
        searchPagingSource?.invalidate()
    }

    private fun handleOnFilterClicked() {
        if (!_stateFlow.value.areTagsLoaded) {
            viewModelScope.launch {
                fetchTagsUseCase().onSuccess {
                    _stateFlow.value = _stateFlow.value.copy(
                        areTagsLoaded = true,
                        tagsList = it.map(TagDomainModel::toUiModel).toMutableStateList() // recomposition goes brrr
                    )
                }.onFailure {
                    _actionFlow.emit(SearchAction.ShowMessage(it.message ?: "Unknown error"))
                }
            }
        }
        _stateFlow.value = _stateFlow.value.copy(
            isExpanded = !_stateFlow.value.isExpanded,
        )
    }
}
