package ru.kpfu.minn.feature.profile.impl.utils

import androidx.compose.runtime.mutableStateOf
import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.model.UserDomainModel
import ru.kpfu.minn.feature.profile.impl.ui.model.ImageUiModel

fun ImageUrl.toDomainModel(): ImageUrlDomainModel = ImageUrlDomainModel(this.imageUrl)

fun ImageUrlDomainModel.toDataModel(): ImageUrl = ImageUrl(this.imageUrl)

fun UserDetails.toDomainModel(): UserDomainModel = UserDomainModel(this.username, this.imageUrl)

fun ImageUrlDomainModel.toUiModel(isLiked: Boolean): ImageUiModel = ImageUiModel(this.imageUrl, mutableStateOf(isLiked))

fun ImageUiModel.toDomainModel(): ImageUrlDomainModel = ImageUrlDomainModel(this.imageUrl)
