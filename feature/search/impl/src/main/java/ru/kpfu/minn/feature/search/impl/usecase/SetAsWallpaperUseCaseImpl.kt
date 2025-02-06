package ru.kpfu.minn.feature.search.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.repository.UserRepository
import ru.kpfu.minn.feature.search.api.usecase.SetAsWallpaperUseCase
import javax.inject.Inject

internal class SetAsWallpaperUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val userRepository: UserRepository,
): SetAsWallpaperUseCase {
    override suspend fun invoke(imageDomainModel: ImageDomainModel): Result<Unit> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            userRepository.setAsWallpaper(imageDomainModel)
        }
    }

}
