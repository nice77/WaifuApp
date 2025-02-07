package ru.kpfu.minn.feature.messaging.api.route

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoute(
    @SerialName("chatId")
    val chatId: String?,
    @SerialName("otherUserId")
    val otherUserId: String,
)
