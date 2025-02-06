package ru.kpfu.minn.feature.search.impl.di

import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.core.data.api.waifuimages.datasource.WaifuImagesDatasource

interface SearchDependencies: ComponentDependencies {

    val waifuImagesDatasource: WaifuImagesDatasource

    val exceptionHandlerDelegate: ExceptionHandlerDelegate

    val userDatasource: UserDatasource

    val favoritesDatasource: FavoritesDatasource

}