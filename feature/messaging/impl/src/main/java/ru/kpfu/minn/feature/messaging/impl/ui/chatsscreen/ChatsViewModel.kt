package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.minn.core.common.BaseViewModel
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatsUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetUserDetailsUseCase
import ru.kpfu.minn.feature.messaging.impl.di.DaggerMessagingComponent
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.model.UserAndChatUiModel
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsAction
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsIntent
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsState
import javax.inject.Inject

class ChatsViewModel(
    dependencies: MessagingDependencies
): BaseViewModel<ChatsAction, ChatsState, ChatsIntent>(
    initialState = ChatsState()
) {

    @Inject
    lateinit var getChatsUseCase: GetChatsUseCase

    @Inject
    lateinit var getUserDetailsUseCase: GetUserDetailsUseCase

    private val component = DaggerMessagingComponent
        .factory()
        .create(dependencies)
        .also {
            it.inject(this@ChatsViewModel)
            initializeChatsList()
        }

    override fun handleIntent(intent: ChatsIntent) {
        when (intent) {
            is ChatsIntent.OnChatClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(ChatsAction.NavigateToChat(intent.chatId, intent.otherUserId))
                }
            }
        }
    }

    private fun initializeChatsList() {
        viewModelScope.launch {
            getChatsUseCase().onSuccess { chats ->
                val uiChats = chats.map { chat ->
                    val user = getUserDetailsUseCase(chat.userId).getOrThrow()
                    UserAndChatUiModel(
                        userId = user.userId,
                        userImage = user.imageUrl,
                        username = user.username,
                        chatId = chat.chatId
                    )
                }
                _stateFlow.value = _stateFlow.value.copy(
                    chats = uiChats,
                )
            }
        }
    }

}