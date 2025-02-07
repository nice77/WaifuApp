package ru.kpfu.minn.core.data.api.messaging

import ru.kpfu.minn.core.data.api.messaging.model.ChatModel
import ru.kpfu.minn.core.data.api.messaging.model.UserAndChat

interface ChatsDatasource {

    suspend fun getChats(): List<UserAndChat>

    suspend fun addChat(
        chatModel: ChatModel,
        currentUserAndChat: UserAndChat,
        otherUserAndChat: UserAndChat
    )

}