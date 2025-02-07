package ru.kpfu.minn.feature.messaging.api.usecase

import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel

interface GetUserDetailsUseCase {

    suspend operator fun invoke(userId: String): Result<UserDetailsDomainModel>

}