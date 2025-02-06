package ru.kpfu.minn.core.data.impl.network.waifuimages

import io.ktor.client.call.body
import io.ktor.client.request.url
import ru.kpfu.minn.core.data.api.waifuimages.CustomHttpClient
import ru.kpfu.minn.core.data.api.waifuimages.datasource.WaifuImagesDatasource
import ru.kpfu.minn.core.data.api.waifuimages.model.ImageContainer
import ru.kpfu.minn.core.data.api.waifuimages.model.ImageModel
import ru.kpfu.minn.core.data.api.waifuimages.model.TagContainer
import ru.kpfu.minn.core.data.api.waifuimages.model.TagModel
import ru.kpfu.minn.core.data.impl.network.di.DefaultUrl
import javax.inject.Inject

internal class WaifuImagesDatasourceImpl @Inject constructor(
    private val customHttpClient: CustomHttpClient,
    @DefaultUrl private val baseUrl: String,
): WaifuImagesDatasource {

    override suspend fun fetchTags(): TagContainer {
        return customHttpClient.get(
            urlString = baseUrl + "tags"
        ).body<TagContainer>()
    }

    override suspend fun fetchImagesByTags(tags: List<TagModel>): List<ImageModel> {
        return customHttpClient.get {
            url {
                url(baseUrl)
                pathSegments = listOf("search")
                tags.forEach { tag ->
                    parameters.append("included_tags", tag.tag)
                }
                parameters.append("limit", "10")
            }
        }.body<ImageContainer>().images
    }

}