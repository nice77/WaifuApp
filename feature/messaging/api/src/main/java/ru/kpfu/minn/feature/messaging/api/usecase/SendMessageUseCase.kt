package ru.kpfu.minn.feature.messaging.api.usecase

import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel

interface SendMessageUseCase {

    suspend operator fun invoke(
        chatId: String,
        otherUserId: String,
        messageDomainModel: MessageDomainModel
    ): Result<MessageDomainModel>

}