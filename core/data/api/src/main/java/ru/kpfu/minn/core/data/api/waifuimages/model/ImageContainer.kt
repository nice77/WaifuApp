package ru.kpfu.minn.core.data.api.waifuimages.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageContainer(
    @SerialName("images")
    val images: List<ImageModel>,
)
