package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatIdByOtherUserUseCase
import javax.inject.Inject

internal class GetChatIdByOtherUserUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val chatsRepository: ChatsRepository,
): GetChatIdByOtherUserUseCase {
    override suspend fun invoke(otherUserId: String): Result<String?> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            println("in usecase")
            chatsRepository.getChatByOtherUserId(otherUserId)
        }
    }
}
