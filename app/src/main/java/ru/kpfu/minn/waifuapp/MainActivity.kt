package ru.kpfu.minn.waifuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kpfu.minn.auth.api.router.AuthRoute
import ru.kpfu.minn.auth.impl.ui.AuthScreen
import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.core.systemdesign.theme.WaifuAppTheme
import ru.kpfu.minn.waifuapp.di.AppComponent
import ru.kpfu.minn.waifuapp.di.ComponentDependenciesManager
import ru.kpfu.minn.waifuapp.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity: ComponentActivity(), DependenciesContainer {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var componentDependenciesManager: ComponentDependenciesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent
            .factory()
            .create()
            .also {
                it.inject(this@MainActivity)
            }

        setContent {
            val navController = rememberNavController()
            WaifuAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = AuthRoute::class
                ) {
                    composable<AuthRoute> {
                        AuthScreen(
                            onRegisterClicked = {  }
                        )
                    }
                }
            }
        }
    }

    override fun <T : ComponentDependencies> getDependencies(key: Class<T>): T =
        componentDependenciesManager.getDependencies(key)
}