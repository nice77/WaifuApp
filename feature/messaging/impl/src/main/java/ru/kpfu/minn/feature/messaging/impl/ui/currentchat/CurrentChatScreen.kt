package ru.kpfu.minn.feature.messaging.impl.ui.currentchat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi.ChatIntent

@Composable
fun CurrentChatScreen(
    chatId: String?,
    otherUserId: String,
) {
    val dependencies = (LocalContext.current as DependenciesContainer).getDependencies(MessagingDependencies::class.java)
    val viewModel = viewModel {
        CurrentChatViewModel(
            dependencies = dependencies,
            chatId = chatId,
            otherUserId = otherUserId,
        )
    }
    val state by viewModel.stateFlow.collectAsState()
    ObserveState(
        state = state,
        onMessageInput = { viewModel.handleIntent(ChatIntent.OnMessageInput(it)) },
        onMessageSendClicked = { viewModel.handleIntent(ChatIntent.OnMessageSendClicked) }
    )
}