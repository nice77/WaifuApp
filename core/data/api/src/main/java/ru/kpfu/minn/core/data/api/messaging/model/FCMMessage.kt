package ru.kpfu.minn.core.data.api.messaging.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FCMMessage(
    @SerialName("to")
    val fcmToken: String,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String,
)