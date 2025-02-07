package ru.kpfu.minn.feature.register.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.register.api.repository.CloudMessagingRepository
import ru.kpfu.minn.feature.register.api.usecase.RetrieveFCMTokenUseCase
import javax.inject.Inject

internal class RetrieveFCMTokenUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val cloudMessagingRepository: CloudMessagingRepository
): RetrieveFCMTokenUseCase {

    override suspend fun invoke(): Result<String> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            cloudMessagingRepository.retrieveToken()
        }
    }

}
