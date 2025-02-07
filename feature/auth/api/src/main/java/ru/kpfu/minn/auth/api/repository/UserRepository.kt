package ru.kpfu.minn.auth.api.repository

interface UserRepository {

    suspend fun updateUserFCMToken(token: String)

}