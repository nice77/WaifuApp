package ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.mvi.ChatsState

@Composable
fun ObserveState(
    modifier: Modifier = Modifier,
    state: ChatsState,
    onChatClicked: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(state.chats.size) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = { onChatClicked(state.chats[item].chatId, state.chats[item].userId) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = state.chats[item].userImage,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(72.dp)
                            .padding(4.dp),
                    )
                    Text(
                        text = state.chats[item].username,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp),
                    )
                }
            }
        }
    }
}