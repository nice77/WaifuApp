package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collect
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsAction
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsIntent

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    navigateToChat: (String, String) -> Unit,
) {
    val dependencies = (LocalContext.current as DependenciesContainer).getDependencies(MessagingDependencies::class.java)
    val viewModel = viewModel {
        ChatsViewModel(dependencies)
    }
    val state by viewModel.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.actionFlow.collect {
            when (it) {
                is ChatsAction.NavigateToChat -> navigateToChat(it.chatId, it.otherUserId)
                is ChatsAction.ShowSnackbar -> snackbarHostState.showSnackbar(message = it.message)
            }
        }
    }
    ObserveState(
        state = state,
        modifier = modifier,
        onChatClicked = { chatId, userId -> viewModel.handleIntent(ChatsIntent.OnChatClicked(chatId, userId)) },
    )
    SnackbarHost(hostState = snackbarHostState)
}