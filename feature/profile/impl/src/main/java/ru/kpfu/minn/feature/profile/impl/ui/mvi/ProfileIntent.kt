package ru.kpfu.minn.feature.profile.impl.ui.mvi

import ru.kpfu.minn.feature.profile.impl.ui.model.ImageUiModel

sealed interface ProfileIntent {
    data object OnImageLikeButtonClicked: ProfileIntent
    data object OnSetAsWallpaperClicked: ProfileIntent
    data class OnImageClicked(val imageUiModel: ImageUiModel): ProfileIntent
    data object OnDismissDialogClicked: ProfileIntent
    data object OnRefresh: ProfileIntent
}