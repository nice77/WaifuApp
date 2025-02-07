package ru.kpfu.minn.feature.search.api.usecase

import ru.kpfu.minn.feature.search.api.model.UserDomainModel

interface GetIsCurrentUserUseCase {

    suspend operator fun invoke(userDomainModel: UserDomainModel): Result<Boolean>

}