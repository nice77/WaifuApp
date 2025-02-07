package ru.kpfu.minn.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.api.usecase.RemoveFromFavoritesUseCase
import ru.kpfu.minn.feature.profile.impl.utils.toDataModel
import javax.inject.Inject

class RemoveFromFavoritesUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
): RemoveFromFavoritesUseCase {
    override suspend fun invoke(imageUrlDomainModel: ImageUrlDomainModel): Result<Boolean> = withContext(
        dispatcher
    ) {
        runSuspendCatching(exceptionHandlerDelegate) {
            favoritesRepository.deleteFromFavorites(imageUrlDomainModel)
        }
    }
}