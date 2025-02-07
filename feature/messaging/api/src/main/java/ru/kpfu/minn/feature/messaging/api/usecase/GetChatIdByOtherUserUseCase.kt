package ru.kpfu.minn.feature.messaging.api.usecase

interface GetChatIdByOtherUserUseCase {

    suspend operator fun invoke(otherUserId: String): Result<String?>

}