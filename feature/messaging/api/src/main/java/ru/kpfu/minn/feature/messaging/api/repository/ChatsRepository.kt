package ru.kpfu.minn.feature.messaging.api.repository

import ru.kpfu.minn.feature.messaging.api.model.ChatDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel

interface ChatsRepository {

    suspend fun getChats(): List<UserAndChatDomainModel>

    suspend fun addChat(
        chatDomainModel: ChatDomainModel,
        currentUserAndChatDomainModel: UserAndChatDomainModel,
        otherUserAndChatDomainModel: UserAndChatDomainModel,
    )

    suspend fun getChatByOtherUserId(otherUserId: String): String?

}