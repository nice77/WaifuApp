package ru.kpfu.minn.feature.register.api.usecase

interface RetrieveFCMTokenUseCase {

    suspend operator fun invoke(): Result<String>

}