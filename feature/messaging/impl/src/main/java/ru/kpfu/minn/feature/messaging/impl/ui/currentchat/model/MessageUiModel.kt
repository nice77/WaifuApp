package ru.kpfu.minn.feature.messaging.impl.ui.currentchat.model

data class MessageUiModel(
    val timestamp: Long,
    val isSentByCurrentUser: Boolean,
    val text: String,
    val time: String,
)
