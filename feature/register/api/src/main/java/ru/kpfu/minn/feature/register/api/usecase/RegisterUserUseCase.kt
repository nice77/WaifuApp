package ru.kpfu.minn.feature.register.api.usecase

import ru.kpfu.minn.feature.register.api.model.UserDomainModel

interface RegisterUserUseCase {

    suspend operator fun invoke(email: String, password: String, username: String): Result<Boolean>
}