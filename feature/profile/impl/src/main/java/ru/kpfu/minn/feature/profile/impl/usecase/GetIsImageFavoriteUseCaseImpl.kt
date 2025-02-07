package ru.kpfu.minn.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.api.usecase.GetIsImageFavoriteUseCase
import javax.inject.Inject

internal class GetIsImageFavoriteUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val favoritesRepository: FavoritesRepository,
): GetIsImageFavoriteUseCase {

    override suspend fun invoke(imageUrlDomainModel: ImageUrlDomainModel): Result<Boolean> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            favoritesRepository.getIsImageFavorite(imageUrlDomainModel)
        }
    }

}
