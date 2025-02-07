package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi

import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.model.UserAndChatUiModel

data class ChatsState(
    val chats: List<UserAndChatUiModel> = listOf()
)
