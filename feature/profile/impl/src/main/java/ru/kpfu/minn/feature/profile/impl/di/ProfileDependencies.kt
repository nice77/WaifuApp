package ru.kpfu.minn.feature.profile.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource


interface ProfileDependencies: ComponentDependencies {

    val favoritesDatasource: FavoritesDatasource

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

    val userDatasource: UserDatasource

    val userService: UserService

}