package ru.kpfu.minn.auth.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource

interface AuthDependencies: ComponentDependencies {

    val userService: UserService

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

    val cloudMessagingService: CloudMessagingService

    val userDatasource: UserDatasource

}