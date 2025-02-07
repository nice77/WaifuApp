package ru.kpfu.minn.feature.messaging.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.messaging.ChatsDatasource
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.messaging.MessagesDatasource
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource

interface MessagingDependencies: ComponentDependencies {

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

    val messagesDatasource: MessagesDatasource

    val chatsDatasource: ChatsDatasource

    val userDatasource: UserDatasource

    val userService: UserService

    val cloudMessagingService: CloudMessagingService

}