package ru.kpfu.minn.feature.search.api.usecase

import ru.kpfu.minn.feature.search.api.model.ImageDomainModel

interface ManageImageFavoritnessUseCase {

    suspend operator fun invoke(
        imageDomainModel: ImageDomainModel,
        toRemove: Boolean,
    ): Result<Unit>

}