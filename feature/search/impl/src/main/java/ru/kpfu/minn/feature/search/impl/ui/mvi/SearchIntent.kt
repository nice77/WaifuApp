package ru.kpfu.minn.feature.search.impl.ui.mvi

import ru.kpfu.minn.feature.search.impl.ui.model.SearchUiModel
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel

sealed interface SearchIntent {
    data object OnFilterClicked: SearchIntent
    data object OnApplyFilterClicked: SearchIntent
    data class OnTextChanged(val text: String): SearchIntent
    data class OnSafeSearchChecked(val isChecked: Boolean): SearchIntent
    data object OnSearchClicked: SearchIntent
    data object OnDismissDialogClicked: SearchIntent
    data object OnLikeClicked: SearchIntent
    data object OnSetAsWallpaperClicked: SearchIntent
    data class OnTagClicked(val tagUiModel: TagUiModel): SearchIntent
    data class OnSwitchClicked(val isChecked: Boolean): SearchIntent
    data class OnSearchItemClicked(val searchUiModel: SearchUiModel): SearchIntent
    data class WriteToUser(val userId: String): SearchIntent
}
