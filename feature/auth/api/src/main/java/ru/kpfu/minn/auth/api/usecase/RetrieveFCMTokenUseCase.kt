package ru.kpfu.minn.auth.api.usecase

interface RetrieveFCMTokenUseCase {

    suspend operator fun invoke(): Result<Unit>

}