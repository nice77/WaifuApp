package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi

sealed interface ChatsIntent {
    data class OnChatClicked(val chatId: String, val otherUserId: String): ChatsIntent
}
