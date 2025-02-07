package ru.kpfu.minn.core.data.api.messaging.model

data class MessageModel(
    val senderId: String = "",
    val text: String = "",
    val timestamp: Long = System.currentTimeMillis(),
)
