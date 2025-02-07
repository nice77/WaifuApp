package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.api.usecase.GetUserDetailsUseCase
import javax.inject.Inject

internal class GetUserDetailsUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val usersRepository: UsersRepository,
): GetUserDetailsUseCase{

    override suspend fun invoke(userId: String): Result<UserDetailsDomainModel> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            usersRepository.getUserDetails(userId = userId)
        }
    }

}
