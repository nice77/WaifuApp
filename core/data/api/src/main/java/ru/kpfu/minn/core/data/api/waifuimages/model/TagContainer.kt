package ru.kpfu.minn.core.data.api.waifuimages.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagContainer (
    @SerialName("versatile")
    val versatile: List<String>,
    @SerialName("nsfw")
    val nsfw: List<String>,
)
