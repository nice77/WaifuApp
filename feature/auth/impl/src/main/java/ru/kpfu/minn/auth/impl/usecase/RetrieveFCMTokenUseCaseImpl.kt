package ru.kpfu.minn.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.auth.api.repository.CloudMessagingRepository
import ru.kpfu.minn.auth.api.repository.UserRepository
import ru.kpfu.minn.auth.api.usecase.RetrieveFCMTokenUseCase
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import javax.inject.Inject

internal class RetrieveFCMTokenUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val cloudMessagingRepository: CloudMessagingRepository,
    private val userRepository: UserRepository
): RetrieveFCMTokenUseCase {

    override suspend fun invoke(): Result<Unit> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            val token = cloudMessagingRepository.retrieveToken()
            userRepository.updateUserFCMToken(token)
        }
    }

}