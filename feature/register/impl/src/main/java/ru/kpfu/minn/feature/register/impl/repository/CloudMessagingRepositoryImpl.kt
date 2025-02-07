package ru.kpfu.minn.feature.register.impl.repository

import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.feature.register.api.repository.CloudMessagingRepository
import javax.inject.Inject

internal class CloudMessagingRepositoryImpl @Inject constructor(
    private val cloudMessagingService: CloudMessagingService,
): CloudMessagingRepository {
    override suspend fun retrieveToken(): String {
        return cloudMessagingService.regenerateToken()
    }

}
