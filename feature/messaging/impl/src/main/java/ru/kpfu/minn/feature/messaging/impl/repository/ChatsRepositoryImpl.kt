package ru.kpfu.minn.feature.messaging.impl.repository

import ru.kpfu.minn.core.data.api.messaging.ChatsDatasource
import ru.kpfu.minn.core.data.api.messaging.model.UserAndChat
import ru.kpfu.minn.feature.messaging.api.model.ChatDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.impl.util.toDataModel
import ru.kpfu.minn.feature.messaging.impl.util.toDomainModel
import javax.inject.Inject

internal class ChatsRepositoryImpl @Inject constructor(
    private val chatsDatasource: ChatsDatasource
): ChatsRepository {

    override suspend fun getChats(): List<UserAndChatDomainModel> {
        return chatsDatasource.getChats().map(UserAndChat::toDomainModel)
    }

    override suspend fun addChat(
        chatDomainModel: ChatDomainModel,
        currentUserAndChatDomainModel: UserAndChatDomainModel,
        otherUserAndChatDomainModel: UserAndChatDomainModel
    ) {
        chatsDatasource.addChat(
            chatModel = chatDomainModel.toDataModel(),
            currentUserAndChat = currentUserAndChatDomainModel.toDataModel(),
            otherUserAndChat = otherUserAndChatDomainModel.toDataModel(),
        )
    }

    override suspend fun getChatByOtherUserId(otherUserId: String): String? {
        val chats = chatsDatasource.getChats()
        println("Found chats: $chats")
        println()
        return chatsDatasource.getChats().firstOrNull { it.userId == otherUserId }?.chatId
    }

}
