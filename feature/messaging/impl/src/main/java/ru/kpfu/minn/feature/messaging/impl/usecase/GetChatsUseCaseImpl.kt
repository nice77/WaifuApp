package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatsUseCase
import javax.inject.Inject

internal class GetChatsUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val chatsRepository: ChatsRepository,
): GetChatsUseCase {

    override suspend fun invoke(): Result<List<UserAndChatDomainModel>> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            chatsRepository.getChats()
        }
    }

}
