package ru.kpfu.minn.core.data.api.users.datasource

import ru.kpfu.minn.core.data.api.users.model.UserDetails

interface UserDatasource {

    suspend fun addUser(userDetails: UserDetails): UserDetails

}