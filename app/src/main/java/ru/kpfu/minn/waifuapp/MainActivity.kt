package ru.kpfu.minn.waifuapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import ru.kpfu.minn.auth.api.router.AuthRoute
import ru.kpfu.minn.auth.impl.ui.AuthScreen
import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.systemdesign.theme.WaifuAppTheme
import ru.kpfu.minn.feature.register.api.router.RegisterRoute
import ru.kpfu.minn.feature.register.impl.ui.RegisterScreen
import ru.kpfu.minn.waifuapp.di.AppComponent
import ru.kpfu.minn.waifuapp.di.ComponentDependenciesManager
import ru.kpfu.minn.waifuapp.di.DaggerAppComponent
import ru.kpfu.minn.waifuapp.ui.HolderScreen
import javax.inject.Inject

class MainActivity: ComponentActivity(), DependenciesContainer {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var componentDependenciesManager: ComponentDependenciesManager

    @Inject
    lateinit var stringResProvider: StringResProvider

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
            .also {
                it.inject(this@MainActivity)
            }

        setContent {
            val navController = rememberNavController()
            val startDestination = if (Firebase.auth.currentUser != null) HolderScreen::class else AuthRoute::class
            WaifuAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<AuthRoute> {
                        AuthScreen(
                            onAuthSuccess = {
                                navController.navigate(HolderScreen)
                            },
                            onRegisterClicked = {
                                if (!navController.popBackStack(route = RegisterRoute, inclusive = false)) {
                                    navController.navigate(RegisterRoute)
                                }
                            },
                        )
                    }
                    composable<RegisterRoute> {
                        RegisterScreen(
                            onSignInClicked = {
                                navController.navigate(AuthRoute)
                            }
                        )
                    }
                    composable<HolderScreen> {
                        HolderScreen(
                            stringResProvider = stringResProvider
                        )
                    }
                }
            }
        }
    }

    override fun <T : ComponentDependencies> getDependencies(key: Class<T>): T =
        componentDependenciesManager.getDependencies(key)
}