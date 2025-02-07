package ru.kpfu.minn.feature.messaging.impl.repository

import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.impl.util.toDomainModel
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource,
    private val userService: UserService,
): UsersRepository {

    override suspend fun getUserDetails(userId: String): UserDetailsDomainModel {
        return userDatasource.getUser(userId = userId).toDomainModel()
    }

    override suspend fun getCurrentUserId(): String {
        return userService.getCurrentUserID()!!
    }

}