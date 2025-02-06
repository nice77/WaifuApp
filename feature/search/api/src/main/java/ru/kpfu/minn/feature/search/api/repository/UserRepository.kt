package ru.kpfu.minn.feature.search.api.repository

import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.UserDomainModel

interface UserRepository {

    suspend fun fetchUsers(page: Long, pageSize: Int, searchQuery: String): List<UserDomainModel>

    suspend fun setAsWallpaper(imageDomainModel: ImageDomainModel)

}