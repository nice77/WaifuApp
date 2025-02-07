package ru.kpfu.minn.feature.profile.api.usecase

import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel

interface GetIsImageFavoriteUseCase {

    suspend operator fun invoke(imageUrlDomainModel: ImageUrlDomainModel): Result<Boolean>

}