package ru.kpfu.minn.core.data.api.users.datasource

import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl
import ru.kpfu.minn.core.data.api.users.model.UserDetails

interface UserDatasource {

    suspend fun addUser(userDetails: UserDetails): UserDetails

    suspend fun getUser(userId: String): UserDetails

    suspend fun updateUserAvatar(imageUrl: ImageUrl): Boolean

    suspend fun fetchUsers(page: Long, pageSize: Int, searchQuery: String): List<UserDetails>

}