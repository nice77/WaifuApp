package ru.kpfu.minn.feature.profile.impl.ui.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ImageUiModel(
    val imageUrl: String,
    var isLiked: MutableState<Boolean> = mutableStateOf(true),
)
