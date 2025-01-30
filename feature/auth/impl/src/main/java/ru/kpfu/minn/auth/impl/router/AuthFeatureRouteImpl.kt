package ru.kpfu.minn.auth.impl.router

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.kpfu.minn.auth.api.router.AuthFeatureRoute
import ru.kpfu.minn.auth.api.router.AuthRoute
import javax.inject.Inject

internal class AuthFeatureRouteImpl @Inject constructor(): AuthFeatureRoute {

    override val route: AuthRoute = AuthRoute

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {
    }

}
