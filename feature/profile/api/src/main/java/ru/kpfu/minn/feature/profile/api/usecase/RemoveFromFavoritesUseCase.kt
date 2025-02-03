package ru.kpfu.minn.feature.profile.api.usecase

import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel

interface RemoveFromFavoritesUseCase {

    suspend operator fun invoke(imageUrlDomainModel: ImageUrlDomainModel): Result<Boolean>

}