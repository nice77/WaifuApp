package ru.kpfu.minn.feature.messaging.api.usecase

import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel

interface GetMessagesUseCase {

    suspend operator fun invoke(chatId: String): Result<List<MessageDomainModel>>

}