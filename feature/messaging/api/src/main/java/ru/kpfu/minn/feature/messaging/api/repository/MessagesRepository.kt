package ru.kpfu.minn.feature.messaging.api.repository

import ru.kpfu.minn.feature.messaging.api.model.FCMMessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel

interface MessagesRepository {

    suspend fun sendMessage(chatId: String, messageDomainModel: MessageDomainModel)

    suspend fun getMessages(
        chatId: String,
        pageSize: Int,
        lastTimestamp: Long = 0,
    ): List<MessageDomainModel>

    suspend fun getMessages(chatId: String): List<MessageDomainModel>

    suspend fun sendNotification(fcmMessageDomainModel: FCMMessageDomainModel)

}