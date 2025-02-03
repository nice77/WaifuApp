package ru.kpfu.minn.feature.profile.impl.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.kpfu.minn.core.common.BaseViewModel
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.usecase.AddToFavoritesUseCase
import ru.kpfu.minn.feature.profile.api.usecase.GetUserInfoUseCase
import ru.kpfu.minn.feature.profile.api.usecase.RemoveFromFavoritesUseCase
import ru.kpfu.minn.feature.profile.api.usecase.SetAsWallpaperUseCase
import ru.kpfu.minn.feature.profile.impl.di.DaggerProfileComponent
import ru.kpfu.minn.feature.profile.impl.di.ProfileDependencies
import ru.kpfu.minn.feature.profile.impl.repository.paging.FavoritesPagingSource
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileAction
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileIntent
import ru.kpfu.minn.feature.profile.impl.ui.mvi.ProfileState
import ru.kpfu.minn.feature.profile.impl.utils.toDomainModel
import ru.kpfu.minn.feature.profile.impl.utils.toUiModel
import javax.inject.Inject

internal class ProfileViewModel(
    dependencies: ProfileDependencies,
    private var userId: String?,
): BaseViewModel<ProfileAction, ProfileState, ProfileIntent>(
    initialState = ProfileState()
) {

    private val component = DaggerProfileComponent
        .factory()
        .create(dependencies)
        .also {
            it.inject(this@ProfileViewModel)
            loadUserInfo()
        }

    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    @Inject
    lateinit var removeFromFavoritesUseCase: RemoveFromFavoritesUseCase

    @Inject
    lateinit var addToFavoritesUseCase: AddToFavoritesUseCase

    @Inject
    lateinit var setAsWallpaperUseCase: SetAsWallpaperUseCase

    private var pagingSource: FavoritesPagingSource? = null

    val imagesFlow = Pager(
        PagingConfig(10)
    ) {
        pagingSource = component.favoritesPagingSource().create(userId)
        pagingSource!!
    }.flow
        .map { pagingData ->
            pagingData.map(ImageUrlDomainModel::toUiModel)
        }
        .cachedIn(viewModelScope)

    private fun loadUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase(userId = userId).onSuccess { user ->
                _stateFlow.value = _stateFlow.value.copy(
                    username = user.username,
                    imageUrl = user.imageUrl,
                    isUserLoading = false,
                )
            }.onFailure { e ->
                when (e) {

                }
            }
        }
    }

    override fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.OnDismissDialogClicked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isDialogShown = false,
                )
            }
            is ProfileIntent.OnImageClicked -> {
                _stateFlow.value = _stateFlow.value.copy(
                    isDialogShown = true,
                    clickedImage = intent.imageUiModel
                )
            }
            ProfileIntent.OnImageLikeButtonClicked -> {
                manageFavorite()
                _stateFlow.value.clickedImage.isLiked.value =
                    !_stateFlow.value.clickedImage.isLiked.value
            }
            is ProfileIntent.OnSetAsWallpaperClicked -> {
                viewModelScope.launch {
                    _stateFlow.value = _stateFlow.value.copy(imageUrl = _stateFlow.value.clickedImage.imageUrl)
                    setAsWallpaperUseCase(_stateFlow.value.clickedImage.toDomainModel())
                }
            }
            ProfileIntent.OnRefresh -> {
                pagingSource?.invalidate()
            }
        }
    }

    private fun manageFavorite() {
        viewModelScope.launch {
            if (_stateFlow.value.clickedImage.isLiked.value) {
                removeFromFavoritesUseCase(_stateFlow.value.clickedImage.toDomainModel())
            } else {
                addToFavoritesUseCase(_stateFlow.value.clickedImage.toDomainModel())
            }
        }
    }
}
