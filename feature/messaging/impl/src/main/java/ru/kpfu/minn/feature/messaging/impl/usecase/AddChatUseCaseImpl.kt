package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.model.ChatDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserAndChatDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.api.usecase.AddChatUseCase
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class AddChatUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val chatsRepository: ChatsRepository,
    private val usersRepository: UsersRepository,
): AddChatUseCase {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun invoke(otherUserId: String): Result<String> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            val currentUserId = usersRepository.getCurrentUserId()
            val chatId = Uuid.random().toString()
            val chatDomainModel = ChatDomainModel(
                chatId = chatId,
            )
            chatsRepository.addChat(
                chatDomainModel = chatDomainModel,
                otherUserAndChatDomainModel = UserAndChatDomainModel(
                    userId = otherUserId,
                    chatId = chatId,
                ),
                currentUserAndChatDomainModel = UserAndChatDomainModel(
                    userId = currentUserId,
                    chatId = chatId,
                ),
            )
            chatId
        }
    }
}