package ru.kpfu.minn.feature.messaging.api.usecase

interface AddChatUseCase {

    suspend operator fun invoke(otherUserId: String): Result<String>
}