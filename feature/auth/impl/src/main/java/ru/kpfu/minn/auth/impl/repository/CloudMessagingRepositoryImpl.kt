package ru.kpfu.minn.auth.impl.repository

import ru.kpfu.minn.auth.api.repository.CloudMessagingRepository
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import javax.inject.Inject

internal class CloudMessagingRepositoryImpl @Inject constructor(
    private val cloudMessagingService: CloudMessagingService,
): CloudMessagingRepository {
    override suspend fun retrieveToken(): String {
        return cloudMessagingService.regenerateToken()
    }
}