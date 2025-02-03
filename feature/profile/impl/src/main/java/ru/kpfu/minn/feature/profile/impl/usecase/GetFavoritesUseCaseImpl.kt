package ru.kpfu.minn.feature.profile.impl.usecase

import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.api.usecase.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
): GetFavoritesUseCase {

    override suspend fun invoke(userId: String): List<ImageUrlDomainModel> {
        TODO("Not yet implemented")
    }

}