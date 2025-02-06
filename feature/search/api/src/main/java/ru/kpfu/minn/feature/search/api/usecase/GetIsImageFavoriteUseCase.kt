package ru.kpfu.minn.feature.search.api.usecase

import ru.kpfu.minn.feature.search.api.model.ImageDomainModel

interface GetIsImageFavoriteUseCase {

    suspend operator fun invoke(imageDomainModel: ImageDomainModel): Result<Boolean>

}