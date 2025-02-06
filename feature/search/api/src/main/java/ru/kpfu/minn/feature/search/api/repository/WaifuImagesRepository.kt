package ru.kpfu.minn.feature.search.api.repository

import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel

interface WaifuImagesRepository {

    suspend fun fetchTags(): List<TagDomainModel>

    suspend fun fetchImages(tags: List<TagDomainModel>): List<ImageDomainModel>

    suspend fun removeFromFavorite(imageDomainModel: ImageDomainModel)

    suspend fun addToFavorite(imageDomainModel: ImageDomainModel)

    suspend fun isFavorite(imageDomainModel: ImageDomainModel): Boolean

}