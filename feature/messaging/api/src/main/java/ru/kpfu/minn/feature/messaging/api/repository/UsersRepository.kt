package ru.kpfu.minn.feature.messaging.api.repository

import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel

interface UsersRepository {

    suspend fun getUserDetails(userId: String): UserDetailsDomainModel

    suspend fun getCurrentUserId(): String

}