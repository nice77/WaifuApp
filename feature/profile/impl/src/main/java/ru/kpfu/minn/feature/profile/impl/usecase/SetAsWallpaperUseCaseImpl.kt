package ru.kpfu.minn.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.UserRepository
import ru.kpfu.minn.feature.profile.api.usecase.SetAsWallpaperUseCase
import javax.inject.Inject

class SetAsWallpaperUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val userRepository: UserRepository,
): SetAsWallpaperUseCase {
    override suspend fun invoke(imageUrlDomainModel: ImageUrlDomainModel): Result<Boolean> =
        withContext(dispatcher) {
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.updateUserAvatar(imageUrlDomainModel = imageUrlDomainModel)
            }
        }
}