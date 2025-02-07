package ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi

sealed interface ChatAction {
    data class ShowSnackbar(val message: String): ChatAction
}