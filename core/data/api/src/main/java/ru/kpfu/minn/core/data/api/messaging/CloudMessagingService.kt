package ru.kpfu.minn.core.data.api.messaging

import ru.kpfu.minn.core.data.api.messaging.model.FCMMessage

interface CloudMessagingService {

    suspend fun getToken(): String

    suspend fun regenerateToken(): String

    suspend fun sendNotification(fcmMessage: FCMMessage)
}