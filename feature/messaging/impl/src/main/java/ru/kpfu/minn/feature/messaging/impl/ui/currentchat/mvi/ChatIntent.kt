package ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi

sealed interface ChatIntent {

    data class OnMessageInput(val message: String): ChatIntent

    data object OnMessageSendClicked: ChatIntent

}