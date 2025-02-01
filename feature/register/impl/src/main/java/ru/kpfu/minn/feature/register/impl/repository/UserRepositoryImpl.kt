package ru.kpfu.minn.feature.register.impl.repository

import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.feature.register.api.model.UserDomainModel
import ru.kpfu.minn.feature.register.api.repository.UserRepository
import ru.kpfu.minn.feature.register.impl.utils.toUserDetails
import ru.kpfu.minn.feature.register.impl.utils.toUserDomainModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource
): UserRepository {

    override suspend fun addUser(userDomainModel: UserDomainModel): UserDomainModel {
        return userDatasource.addUser(userDomainModel.toUserDetails()).toUserDomainModel()
    }

}