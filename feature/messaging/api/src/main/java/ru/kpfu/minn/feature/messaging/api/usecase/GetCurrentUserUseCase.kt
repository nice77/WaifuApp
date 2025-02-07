package ru.kpfu.minn.feature.messaging.api.usecase

interface GetCurrentUserUseCase {

    suspend operator fun invoke(): Result<String>

}