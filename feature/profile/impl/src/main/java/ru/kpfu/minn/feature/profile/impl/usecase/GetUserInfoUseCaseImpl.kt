package ru.kpfu.minn.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.profile.api.model.UserDomainModel
import ru.kpfu.minn.feature.profile.api.repository.UserRepository
import ru.kpfu.minn.feature.profile.api.usecase.GetUserInfoUseCase
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val userRepository: UserRepository,
): GetUserInfoUseCase {

    override suspend fun invoke(userId: String?): Result<UserDomainModel> =
        withContext(dispatcher) {
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.fetchUserInfo(userId = userId)
            }
        }
}