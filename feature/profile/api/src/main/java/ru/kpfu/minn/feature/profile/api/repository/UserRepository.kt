package ru.kpfu.minn.feature.profile.api.repository

import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.model.UserDomainModel

interface UserRepository {

    suspend fun fetchUserInfo(userId: String?): UserDomainModel

    suspend fun updateUserAvatar(imageUrlDomainModel: ImageUrlDomainModel): Boolean

}