package ru.kpfu.minn.core.data.api.messaging

import ru.kpfu.minn.core.data.api.messaging.model.MessageModel

interface MessagesDatasource {

    suspend fun sendMessage(chatId: String, messageModel: MessageModel)

    suspend fun getMessages(
        chatId: String,
        lastTimestamp: Long,
        pageSize: Int,
    ): List<MessageModel>

    suspend fun getMessages(chatId: String): List<MessageModel>

}