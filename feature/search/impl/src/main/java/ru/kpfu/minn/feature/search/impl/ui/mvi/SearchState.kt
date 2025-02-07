package ru.kpfu.minn.feature.search.impl.ui.mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import ru.kpfu.minn.feature.search.impl.ui.model.SearchUiModel
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel

data class SearchState(
    val isExpanded: Boolean = false,
    val searchQuery: String = "",
    val areTagsLoaded: Boolean = false,
    val isSafeSearchEnabled: Boolean = true,
    val tagsList: MutableList<TagUiModel> = mutableStateListOf(),
    val isImageSearch: Boolean = false,
    val isDialogShown: Boolean = false,
    val clickedImage: SearchUiModel.ImageUiModel = SearchUiModel.ImageUiModel("", ""),
    val isLoading: MutableState<Boolean> = mutableStateOf(false),
)
