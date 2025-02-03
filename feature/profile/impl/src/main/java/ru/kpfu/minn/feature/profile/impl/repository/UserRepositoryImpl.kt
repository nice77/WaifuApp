package ru.kpfu.minn.feature.profile.impl.repository

import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.model.UserDomainModel
import ru.kpfu.minn.feature.profile.api.repository.UserRepository
import ru.kpfu.minn.feature.profile.impl.utils.toDataModel
import ru.kpfu.minn.feature.profile.impl.utils.toDomainModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource,
    private val userService: UserService,
): UserRepository {

    override suspend fun fetchUserInfo(userId: String?): UserDomainModel =
        userDatasource.getUser(userId ?: userService.getCurrentUserID()!!).toDomainModel()

    override suspend fun updateUserAvatar(imageUrlDomainModel: ImageUrlDomainModel): Boolean =
        userDatasource.updateUserAvatar(imageUrlDomainModel.toDataModel())

}