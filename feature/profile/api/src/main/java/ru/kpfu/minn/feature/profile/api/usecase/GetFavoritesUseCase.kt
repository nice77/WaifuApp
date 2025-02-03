package ru.kpfu.minn.feature.profile.api.usecase

import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel

interface GetFavoritesUseCase {

    suspend operator fun invoke(userId: String): List<ImageUrlDomainModel>

}