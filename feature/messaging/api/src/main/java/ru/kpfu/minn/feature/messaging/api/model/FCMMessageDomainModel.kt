package ru.kpfu.minn.feature.messaging.api.model

data class FCMMessageDomainModel(
    val fcmToken: String,
    val title: String,
    val body: String,
)
