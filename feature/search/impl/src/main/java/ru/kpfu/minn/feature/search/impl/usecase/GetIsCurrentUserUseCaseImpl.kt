package ru.kpfu.minn.feature.search.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.api.repository.UserRepository
import ru.kpfu.minn.feature.search.api.usecase.GetIsCurrentUserUseCase
import javax.inject.Inject

internal class GetIsCurrentUserUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val userRepository: UserRepository,
): GetIsCurrentUserUseCase {

    override suspend fun invoke(userDomainModel: UserDomainModel): Result<Boolean> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            userRepository.getIsCurrentUser(userDomainModel)
        }
    }

}
