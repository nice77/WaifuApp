package ru.kpfu.minn.feature.messaging.api.usecase

import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel

interface GetChatsUseCase {

    suspend operator fun invoke(): Result<List<UserAndChatDomainModel>>

}