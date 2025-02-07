package ru.kpfu.minn.waifuapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import ru.kpfu.minn.auth.api.router.AuthRoute
import ru.kpfu.minn.auth.impl.ui.AuthScreen
import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.systemdesign.theme.WaifuAppTheme
import ru.kpfu.minn.feature.messaging.api.route.ChatRoute
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.CurrentChatScreen
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

    @Inject
    lateinit var cloudMessagingService: CloudMessagingService

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(this, this.getString(R.string.rationale), Toast.LENGTH_LONG).show()
        }
    }

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
                            },
                            onRegisterSuccess = {
                                navController.navigate(HolderScreen)
                            }
                        )
                    }
                    composable<HolderScreen> {
                        HolderScreen(
                            stringResProvider = stringResProvider,
                            parentNavController = navController,
                        )
                    }
                    composable<ChatRoute> {
                        val arguments = it.toRoute<ChatRoute>()
                        CurrentChatScreen(arguments.chatId, arguments.otherUserId)
                    }
                }
            }
        }
        askForNotificationPermission()
    }

    private fun askForNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                return
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                Toast.makeText(this, this.getString(R.string.rationale), Toast.LENGTH_LONG).show()
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun <T : ComponentDependencies> getDependencies(key: Class<T>): T =
        componentDependenciesManager.getDependencies(key)

    companion object {
        const val TOPIC_NAME = "WAIFU_APP_TOPIC"
    }
}