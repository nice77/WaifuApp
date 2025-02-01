package ru.kpfu.minn.feature.register.api.repository

import ru.kpfu.minn.feature.register.api.model.UserDomainModel

interface UserRepository {

    suspend fun addUser(userDomainModel: UserDomainModel): UserDomainModel

}