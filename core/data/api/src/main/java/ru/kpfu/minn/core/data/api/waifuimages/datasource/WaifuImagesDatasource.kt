package ru.kpfu.minn.core.data.api.waifuimages.datasource

import ru.kpfu.minn.core.data.api.waifuimages.model.ImageModel
import ru.kpfu.minn.core.data.api.waifuimages.model.TagContainer
import ru.kpfu.minn.core.data.api.waifuimages.model.TagModel

interface WaifuImagesDatasource {

    suspend fun fetchTags(): TagContainer

    suspend fun fetchImagesByTags(tags: List<TagModel>): List<ImageModel>

}