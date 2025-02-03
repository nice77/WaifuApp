package ru.kpfu.minn.waifuapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.profile.api.router.ProfileRoute
import ru.kpfu.minn.feature.profile.impl.ui.ProfileScreen

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
                onSearchClicked = {  },
                onProfileClicked = {  },
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ProfileRoute,
        ) {
            composable<ProfileRoute> {
                ProfileScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }
        }
    }
}