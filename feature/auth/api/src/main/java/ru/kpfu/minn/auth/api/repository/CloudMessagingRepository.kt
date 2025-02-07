package ru.kpfu.minn.auth.api.repository


interface CloudMessagingRepository {

    suspend fun retrieveToken(): String

}