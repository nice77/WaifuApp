package ru.kpfu.minn.auth.api.router

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.kpfu.minn.core.common.router.FeatureRoute

interface AuthFeatureRoute: FeatureRoute {

    val route: AuthRoute

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
    )
}
