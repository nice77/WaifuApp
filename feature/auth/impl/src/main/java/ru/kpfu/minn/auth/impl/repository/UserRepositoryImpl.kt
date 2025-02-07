package ru.kpfu.minn.auth.impl.repository

import ru.kpfu.minn.auth.api.repository.UserRepository
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource
): UserRepository {
    override suspend fun updateUserFCMToken(token: String) {
        userDatasource.updateUserToken(token)
    }
}