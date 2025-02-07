package ru.kpfu.minn.feature.messaging.api.model

data class UserDetailsDomainModel(
    val userId: String,
    val username: String,
    val imageUrl: String?,
    val fcmToken: String,
)
