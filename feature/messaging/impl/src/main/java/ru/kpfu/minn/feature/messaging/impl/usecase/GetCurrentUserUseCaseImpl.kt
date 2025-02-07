package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.api.usecase.GetCurrentUserUseCase
import javax.inject.Inject

internal class GetCurrentUserUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val usersRepository: UsersRepository
): GetCurrentUserUseCase {
    override suspend fun invoke(): Result<String> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            usersRepository.getCurrentUserId()
        }
    }

}
