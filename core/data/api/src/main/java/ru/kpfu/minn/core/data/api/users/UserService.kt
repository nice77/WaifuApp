package ru.kpfu.minn.core.data.api.users

interface UserService {

    suspend fun authorizeUser(email: String, password: String): Boolean

}