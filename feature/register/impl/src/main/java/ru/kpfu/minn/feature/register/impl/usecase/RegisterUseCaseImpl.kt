package ru.kpfu.minn.feature.register.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.AppException
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.feature.register.api.model.UserDomainModel
import ru.kpfu.minn.feature.register.api.repository.UserRepository
import ru.kpfu.minn.feature.register.api.usecase.RegisterUserUseCase
import javax.inject.Inject

class RegisterUserUseCaseImpl @Inject constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val dispatcher: CoroutineDispatcher,
): RegisterUserUseCase {

    override suspend fun invoke(
        email: String,
        password: String,
        username: String
    ): Result<Boolean> {
        return withContext(dispatcher) {
            runSuspendCatching(exceptionHandlerDelegate) {
                userService.registerUser(email, password)?.let { user ->
                    userRepository.addUser(
                        UserDomainModel(
                            uid = user.uid,
                            email = user.email,
                            username = username,
                            imageUrl = null,
                        )
                    )
                    true
                } ?: false
            }
        }
    }
}