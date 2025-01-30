package ru.kpfu.minn.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.auth.api.usecase.AuthorizeUserUseCase
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.core.data.api.users.UserService
import javax.inject.Inject

internal class AuthorizeUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val dispatcher: CoroutineDispatcher,
): AuthorizeUserUseCase {

    override suspend fun invoke(email: String, password: String) = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            userService.authorizeUser(email, password)
        }
    }

}