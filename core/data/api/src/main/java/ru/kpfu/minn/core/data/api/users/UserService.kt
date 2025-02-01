package ru.kpfu.minn.core.data.api.users

import ru.kpfu.minn.core.data.api.users.model.User


interface UserService {

    suspend fun authorizeUser(email: String, password: String): Boolean

    suspend fun registerUser(email: String, password: String): User?

    fun getCurrentUserID(): String?

}