package ru.kpfu.minn.feature.register.api.repository

interface CloudMessagingRepository {

    suspend fun retrieveToken(): String

}