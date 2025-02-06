package ru.kpfu.minn.waifuapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.profile.api.router.ProfileRoute
import ru.kpfu.minn.feature.profile.impl.ui.ProfileScreen
import ru.kpfu.minn.feature.search.api.router.SearchRoute
import ru.kpfu.minn.feature.search.impl.ui.SearchScreen

@Serializable
object HolderScreen

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun HolderScreen(
    stringResProvider: StringResProvider,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            CustomBottomBar(
                stringResProvider = stringResProvider,
                onDashboardClicked = {  },
                onSearchClicked = { navController.navigate(SearchRoute) },
                onProfileClicked = { navController.navigate(ProfileRoute()) },
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ProfileRoute(),
        ) {
            composable<ProfileRoute> {
                val arguments = it.toRoute<ProfileRoute>()
                ProfileScreen(
                    modifier = Modifier
                        .padding(paddingValues),
                    userId = arguments.uid,
                )
            }
            composable<SearchRoute> {
                SearchScreen(
                    stringResProvider = stringResProvider,
                    onUserClicked = { navController.navigate(ProfileRoute(it)) },
                    modifier = Modifier
                        .padding(paddingValues),
                )
            }
        }
    }
}