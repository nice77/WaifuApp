package ru.kpfu.minn.feature.messaging.impl.repository

import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.messaging.MessagesDatasource
import ru.kpfu.minn.core.data.api.messaging.model.MessageModel
import ru.kpfu.minn.feature.messaging.api.model.FCMMessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository
import ru.kpfu.minn.feature.messaging.impl.util.toDataModel
import ru.kpfu.minn.feature.messaging.impl.util.toDomainModel
import javax.inject.Inject

internal class MessagesRepositoryImpl @Inject constructor(
    private val messagesDatasource: MessagesDatasource,
    private val cloudMessagingService: CloudMessagingService,
): MessagesRepository {

    override suspend fun sendMessage(chatId: String, messageDomainModel: MessageDomainModel) {
        messagesDatasource.sendMessage(chatId = chatId, messageDomainModel.toDataModel())
    }

    override suspend fun getMessages(
        chatId: String,
        pageSize: Int,
        lastTimestamp: Long,
    ): List<MessageDomainModel> {
        return messagesDatasource.getMessages(
            chatId = chatId,
            lastTimestamp = lastTimestamp,
            pageSize = pageSize,
        ).map(MessageModel::toDomainModel)
    }

    override suspend fun getMessages(chatId: String): List<MessageDomainModel> {
        return messagesDatasource.getMessages(chatId).map(MessageModel::toDomainModel)
    }

    override suspend fun sendNotification(fcmMessageDomainModel: FCMMessageDomainModel) {
        cloudMessagingService.sendNotification(fcmMessageDomainModel.toDataModel())
    }

}
