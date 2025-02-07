package ru.kpfu.minn.feature.messaging.api.model

data class MessageDomainModel(
    val timestamp: Long,
    val userId: String,
    val text: String,
)
