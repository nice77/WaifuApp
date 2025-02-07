package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository
import ru.kpfu.minn.feature.messaging.api.usecase.GetMessagesUseCase
import javax.inject.Inject

internal class GetMessagesUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val messagesRepository: MessagesRepository
): GetMessagesUseCase {

    override suspend fun invoke(chatId: String): Result<List<MessageDomainModel>> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            messagesRepository.getMessages(chatId)
        }
    }

}
