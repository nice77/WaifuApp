package ru.kpfu.minn.feature.messaging.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.messaging.api.model.FCMMessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.api.usecase.SendMessageUseCase
import javax.inject.Inject

internal class SendMessageUseCaseImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
    private val messagesRepository: MessagesRepository,
    private val usersRepository: UsersRepository,
): SendMessageUseCase {
    override suspend fun invoke(
        chatId: String,
        otherUserId: String,
        messageDomainModel: MessageDomainModel
    ): Result<MessageDomainModel> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            messagesRepository.sendMessage(chatId, messageDomainModel)
            val otherUser = usersRepository.getUserDetails(otherUserId)
            val notificationBody = FCMMessageDomainModel(
                fcmToken = otherUser.fcmToken,
                title = otherUser.username,
                body = messageDomainModel.text,
            )
            messagesRepository.sendNotification(notificationBody)
            messageDomainModel
        }
    }
}
