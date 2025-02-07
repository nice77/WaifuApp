package ru.kpfu.minn.feature.messaging.impl.ui.currentchat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.mvi.ChatState

@Composable
fun ObserveState(
    state: ChatState,
    onMessageInput: (String) -> Unit,
    onMessageSendClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            reverseLayout = true,
        ) {
            items(state.messages.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (state.messages[index].isSentByCurrentUser) Arrangement.End else Arrangement.Start
                ) {
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                        ) {
                            Text(
                                text = state.messages[index].text,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(4.dp),
                            )
                            Text(
                                text = state.messages[index].time,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(4.dp),
                            )
                        }
                    }
                }
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = state.inputMessage,
            onValueChange = onMessageInput,
            trailingIcon = {
                IconButton(
                    onClick = onMessageSendClicked
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                    )
                }
            }
        )
    }
}
