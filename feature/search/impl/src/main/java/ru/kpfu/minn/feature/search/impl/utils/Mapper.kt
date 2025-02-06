package ru.kpfu.minn.feature.search.impl.utils

import androidx.compose.runtime.mutableStateOf
import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import ru.kpfu.minn.core.data.api.waifuimages.model.ImageModel
import ru.kpfu.minn.core.data.api.waifuimages.model.TagContainer
import ru.kpfu.minn.core.data.api.waifuimages.model.TagModel
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.impl.ui.model.SearchUiModel
import ru.kpfu.minn.feature.search.impl.ui.model.TagUiModel

fun TagContainer.toDomainModel(): List<TagDomainModel> {
    return this.versatile.map {
        TagDomainModel(
            nsfw = false,
            tag = it
        )
    } + this.nsfw.map {
        TagDomainModel(
            nsfw = true,
            tag = it
        )
    }
}

fun TagDomainModel.toUiModel(): TagUiModel = TagUiModel(selected = false, nsfw = this.nsfw, tag = this.tag)

fun TagUiModel.toDomainModel(): TagDomainModel = TagDomainModel(nsfw = nsfw,  tag = tag)

fun TagDomainModel.toDataModel(): TagModel = TagModel(tag = this.tag)

fun ImageModel.toDomainModel(): ImageDomainModel = ImageDomainModel(url = this.url, previewUrl = this.previewUrl)

fun ImageDomainModel.toUiModel(isLiked: Boolean): SearchUiModel.ImageUiModel =
    SearchUiModel.ImageUiModel(
        imageUrl = this.url,
        previewUrl = this.previewUrl,
        isLiked = mutableStateOf(isLiked),
    )

fun ImageDomainModel.toDataModel(): ImageUrl = ImageUrl(imageUrl = this.url)

fun UserDetails.toDomainModel(): UserDomainModel = UserDomainModel(
    uid = this.uid,
    email = this.email,
    username = this.username,
    imageUrl = this.imageUrl,
)

fun UserDomainModel.toUiModel(): SearchUiModel.UserUiModel = SearchUiModel.UserUiModel(
    uid = this.uid,
    username = this.username,
    imageUrl = this.imageUrl,
)

fun SearchUiModel.ImageUiModel.toDomainModel(): ImageDomainModel = ImageDomainModel(
    url = this.imageUrl,
    previewUrl = this.previewUrl
)
