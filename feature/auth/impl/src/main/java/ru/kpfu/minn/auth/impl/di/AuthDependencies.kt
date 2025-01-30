package ru.kpfu.minn.auth.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.users.UserService

interface AuthDependencies: ComponentDependencies {

    val userService: UserService

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

}