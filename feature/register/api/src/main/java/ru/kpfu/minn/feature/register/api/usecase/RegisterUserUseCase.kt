package ru.kpfu.minn.feature.register.api.usecase


interface RegisterUserUseCase {

    suspend operator fun invoke(email: String, password: String, username: String, fcmToken: String): Result<Boolean>
}