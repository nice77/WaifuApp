package ru.kpfu.minn.core.data.impl.firebase.messaging

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.messaging.MessagesDatasource
import ru.kpfu.minn.core.data.api.messaging.model.MessageModel
import javax.inject.Inject

class MessagesDatasourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
): MessagesDatasource {

    override suspend fun sendMessage(chatId: String, messageModel: MessageModel) {
        println(messageModel)
        firebaseFirestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .add(messageModel)
            .await()
    }

    override suspend fun getMessages(
        chatId: String,
        lastTimestamp: Long,
        pageSize: Int
    ): List<MessageModel> {
        return firebaseFirestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .startAt(lastTimestamp)
            .limit(pageSize.toLong())
            .get()
            .await()
            .toObjects(MessageModel::class.java)
    }

    override suspend fun getMessages(chatId: String): List<MessageModel> {
        return firebaseFirestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .get()
            .await()
            .toObjects(MessageModel::class.java)
    }

}