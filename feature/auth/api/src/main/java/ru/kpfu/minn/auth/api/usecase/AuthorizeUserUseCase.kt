package ru.kpfu.minn.auth.api.usecase

interface AuthorizeUserUseCase {

    suspend operator fun invoke(email: String, password: String): Result<Boolean>

}