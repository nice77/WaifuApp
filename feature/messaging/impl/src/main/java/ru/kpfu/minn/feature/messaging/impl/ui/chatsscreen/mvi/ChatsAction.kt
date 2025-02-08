package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi

sealed interface ChatsAction {
    data class NavigateToChat(val chatId: String, val otherUserId: String): ChatsAction
    data class ShowSnackbar(val message: String): ChatsAction
}
