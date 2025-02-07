package ru.kpfu.minn.core.data.impl.firebase.messaging

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.messaging.ChatsDatasource
import ru.kpfu.minn.core.data.api.messaging.model.ChatModel
import ru.kpfu.minn.core.data.api.messaging.model.UserAndChat
import javax.inject.Inject

internal class ChatsDatasourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
): ChatsDatasource {

    override suspend fun getChats(): List<UserAndChat> {
        val userChatIDs = firestore.collection("users_and_chats")
            .whereEqualTo("userId", firebaseAuth.currentUser!!.uid)
            .get()
            .await()
            .toObjects(UserAndChat::class.java)
            .map { it.chatId }
            .toList()
        return firestore.collection("users_and_chats")
            .whereIn("chatId", userChatIDs)
            .whereNotEqualTo("userId", firebaseAuth.currentUser!!.uid)
            .get()
            .await()
            .toObjects(UserAndChat::class.java)
    }

    override suspend fun addChat(
        chatModel: ChatModel,
        currentUserAndChat: UserAndChat,
        otherUserAndChat: UserAndChat
    ) {
        firestore.collection("chats")
            .document(chatModel.id)
            .set(chatModel)
            .await()
        firestore.collection("users_and_chats")
            .add(currentUserAndChat)
            .await()
        firestore.collection("users_and_chats")
            .add(otherUserAndChat)
            .await()
    }

}
