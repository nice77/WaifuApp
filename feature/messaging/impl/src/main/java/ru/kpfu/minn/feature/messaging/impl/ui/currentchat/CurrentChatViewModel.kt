package ru.kpfu.minn.feature.messaging.impl.ui.currentchat

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.minn.core.common.BaseViewModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.usecase.AddChatUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatIdByOtherUserUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetCurrentUserUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetMessagesUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.SendMessageUseCase
import ru.kpfu.minn.feature.messaging.impl.di.DaggerMessagingComponent
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi.ChatAction
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi.ChatIntent
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi.ChatState
import ru.kpfu.minn.feature.messaging.impl.util.toUiModel
import javax.inject.Inject

internal class CurrentChatViewModel(
    dependencies: MessagingDependencies,
    private var chatId: String?,
    private var otherUserId: String,
): BaseViewModel<ChatAction, ChatState, ChatIntent>(
    initialState = ChatState()
) {

    val component = DaggerMessagingComponent
        .factory()
        .create(dependencies)
        .also {
            it.inject(this@CurrentChatViewModel)
            loadChatMessages()
        }

    @Inject
    lateinit var getMessagesUseCase: GetMessagesUseCase

    @Inject
    lateinit var getCurrentUserUseCase: GetCurrentUserUseCase

    @Inject
    lateinit var sendMessageUseCase: SendMessageUseCase

    @Inject
    lateinit var addChatUseCase: AddChatUseCase

    @Inject
    lateinit var getChatIdByOtherUserUseCase: GetChatIdByOtherUserUseCase

    override fun handleIntent(intent: ChatIntent) {
        when (intent) {
            is ChatIntent.OnMessageInput -> {
                _stateFlow.value = _stateFlow.value.copy(
                    inputMessage = intent.message,
                )
            }
            ChatIntent.OnMessageSendClicked -> onMessageSendClicked()
        }
    }

    private fun onMessageSendClicked() {
        viewModelScope.launch {
            if (chatId == null) {
                addChatUseCase(otherUserId).onSuccess {
                    chatId = it
                }.onFailure {
                    emitErrorMessage(it)
                    return@launch
                }
            }
            getCurrentUserUseCase().onSuccess {
                sendMessageUseCase(
                    chatId = chatId!!,
                    otherUserId = otherUserId,
                    messageDomainModel = MessageDomainModel(
                        timestamp = System.currentTimeMillis(),
                        userId = it,
                        text = _stateFlow.value.inputMessage,
                    )
                ).onSuccess {
                    _stateFlow.value = _stateFlow.value.copy(
                        inputMessage = "",
                    )
                    _stateFlow.value.messages.add(it.toUiModel(isSentByCurrentUser = true))
                }.onFailure { emitErrorMessage(it) }
            }.onFailure {
                emitErrorMessage(it)
            }
        }
    }

    private fun loadChatMessages() {
        viewModelScope.launch {
            getChatIdByOtherUserUseCase(otherUserId).onSuccess {
                chatId = it
            }.onFailure {
                emitErrorMessage(it)
                return@launch
            }
            chatId?.let { existingChat ->
                getCurrentUserUseCase().onSuccess { currentUserId ->
                    getMessagesUseCase(existingChat).onSuccess { result ->
                        _stateFlow.value = _stateFlow.value.copy(
                            messages = result.map {
                                it.toUiModel(
                                    isSentByCurrentUser = it.userId == currentUserId
                                )
                            }.sortedBy { -it.timestamp }.toMutableList()
                        )
                    }.onFailure { emitErrorMessage(it) }
                }.onFailure { emitErrorMessage(it) }
            }
        }
    }

    private suspend fun emitErrorMessage(e: Throwable) {
        _actionFlow.emit(ChatAction.ShowSnackbar(e.message ?: "Unknown error"))
    }
}