package ru.kpfu.minn.feature.search.impl.repository

import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.api.repository.UserRepository
import ru.kpfu.minn.feature.search.impl.utils.toDataModel
import ru.kpfu.minn.feature.search.impl.utils.toDomainModel
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDatasource: UserDatasource,
): UserRepository {

    override suspend fun fetchUsers(page: Long, pageSize: Int, searchQuery: String): List<UserDomainModel> =
        userDatasource.fetchUsers(
            page = page,
            pageSize = pageSize,
            searchQuery = searchQuery
        ).map(UserDetails::toDomainModel)

    override suspend fun setAsWallpaper(imageDomainModel: ImageDomainModel) {
        userDatasource.updateUserAvatar(imageDomainModel.toDataModel())
    }

}
