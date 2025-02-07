package ru.kpfu.minn.core.data.impl.firebase.messaging

import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.messaging.model.FCMMessage
import ru.kpfu.minn.core.data.api.waifuimages.CustomHttpClient
import ru.kpfu.minn.core.data.impl.network.di.FCMUrl
import javax.inject.Inject

class CloudMessagingServiceImpl @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging,
    @FCMUrl private val baseUrl: String,
    private val customHttpClient: CustomHttpClient,
): CloudMessagingService {
    override suspend fun getToken(): String {
        return firebaseMessaging.token.await()
    }

    override suspend fun regenerateToken(): String {
        firebaseMessaging.deleteToken()
        return firebaseMessaging.token.await()
    }

    override suspend fun sendNotification(fcmMessage: FCMMessage) {
        val resp = customHttpClient.post("$baseUrl/send_message") {
            contentType(ContentType.Application.Json)
            setBody(fcmMessage)
        }
        println(resp.body<String>())
    }
}