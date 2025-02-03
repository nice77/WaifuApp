package ru.kpfu.minn.feature.profile.api.usecase

import ru.kpfu.minn.feature.profile.api.model.UserDomainModel

interface GetUserInfoUseCase {

    suspend operator fun invoke(userId: String?): Result<UserDomainModel>

}