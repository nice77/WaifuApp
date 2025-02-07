package ru.kpfu.minn.feature.register.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource

interface RegisterDependencies: ComponentDependencies {

    val userService: UserService

    val userDatasource: UserDatasource

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

    val stringResProvider: StringResProvider

    val cloudMessagingService: CloudMessagingService

}