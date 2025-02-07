package ru.kpfu.minn.feature.messaging.impl.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import ru.kpfu.minn.core.data.api.messaging.model.ChatModel
import ru.kpfu.minn.core.data.api.messaging.model.FCMMessage
import ru.kpfu.minn.core.data.api.messaging.model.MessageModel
import ru.kpfu.minn.core.data.api.messaging.model.UserAndChat
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import ru.kpfu.minn.feature.messaging.api.model.ChatDomainModel
import ru.kpfu.minn.feature.messaging.api.model.FCMMessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.model.MessageUiModel

fun MessageModel.toDomainModel(): MessageDomainModel = MessageDomainModel(
    timestamp = timestamp,
    userId = senderId,
    text = text,
)

fun MessageDomainModel.toDataModel(): MessageModel = MessageModel(
    senderId = userId,
    text = text,
    timestamp = timestamp,
)

fun MessageDomainModel.toUiModel(isSentByCurrentUser: Boolean): MessageUiModel = MessageUiModel(
    timestamp = timestamp,
    isSentByCurrentUser = isSentByCurrentUser,
    text = text,
    time = run {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val timeZone = TimeZone.currentSystemDefault()
        val datetime = instant.toLocalDateTime(timeZone)
        datetime.time.run {
            "${"0".repeat(2 - hour.toString().length) + hour}:${"0".repeat(2 - minute.toString().length) + minute}"
        }
    }
)

fun UserAndChat.toDomainModel(): UserAndChatDomainModel = UserAndChatDomainModel(
    chatId = chatId,
    userId = userId,
)

fun UserAndChatDomainModel.toDataModel(): UserAndChat = UserAndChat(
    chatId = chatId,
    userId = userId,
)

fun ChatModel.toDomainModel(): ChatDomainModel = ChatDomainModel(
    chatId = this.id
)

fun ChatDomainModel.toDataModel(): ChatModel = ChatModel(
    id = chatId
)

fun UserDetails.toDomainModel(): UserDetailsDomainModel = UserDetailsDomainModel(
    userId = uid,
    username = username,
    imageUrl = imageUrl,
    fcmToken = fcmToken,
)

fun FCMMessage.toDomainModel(): FCMMessageDomainModel = FCMMessageDomainModel(
    fcmToken = this.fcmToken,
    title = this.title,
    body = this.body,
)

fun FCMMessageDomainModel.toDataModel(): FCMMessage = FCMMessage(
    fcmToken = this.fcmToken,
    title = this.title,
    body = this.body,
)