package ru.kpfu.minn.feature.search.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository
import ru.kpfu.minn.feature.search.api.usecase.ManageImageFavoritnessUseCase
import javax.inject.Inject

internal class ManageImageFavoritnessUseCaseImpl @Inject constructor(
    private val waifuImagesRepository: WaifuImagesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
): ManageImageFavoritnessUseCase {
    override suspend fun invoke(
        imageDomainModel: ImageDomainModel,
        toRemove: Boolean,
    ): Result<Unit> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            if (toRemove) {
                waifuImagesRepository.removeFromFavorite(imageDomainModel)
            } else {
                waifuImagesRepository.addToFavorite(imageDomainModel)
            }
        }
    }

}
