package ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi

import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.model.MessageUiModel

data class ChatState(
    val inputMessage: String = "",
    val messages: MutableList<MessageUiModel> = mutableListOf(),
)
