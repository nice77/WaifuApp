package ru.kpfu.minn.feature.search.impl.ui.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed interface SearchUiModel {

    data class ImageUiModel (
        val imageUrl: String,
        val previewUrl: String,
        var isLiked: MutableState<Boolean> = mutableStateOf(true),
    ): SearchUiModel

    data class UserUiModel(
        val uid: String,
        val username: String,
        val imageUrl: String?,
    ): SearchUiModel
}