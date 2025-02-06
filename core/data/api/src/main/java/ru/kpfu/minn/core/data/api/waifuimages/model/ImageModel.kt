package ru.kpfu.minn.core.data.api.waifuimages.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageModel(
    @SerialName("url")
    val url: String,
    @SerialName("preview_url")
    val previewUrl: String,
)
