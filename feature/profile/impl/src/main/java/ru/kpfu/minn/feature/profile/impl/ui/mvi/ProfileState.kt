package ru.kpfu.minn.feature.profile.impl.ui.mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.kpfu.minn.feature.profile.impl.ui.model.ImageUiModel


data class ProfileState(
    val username: String = "",
    val imageUrl: String? = null,
    val isDialogShown: Boolean = false,
    val clickedImage: ImageUiModel = ImageUiModel(""),
    val isUserLoading: Boolean = true,
    val refreshing: MutableState<Boolean> = mutableStateOf(false),
)
