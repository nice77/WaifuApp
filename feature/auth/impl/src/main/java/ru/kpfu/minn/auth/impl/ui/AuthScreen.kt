package ru.kpfu.minn.auth.impl.ui

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.auth.impl.ui.model.AuthAction
import ru.kpfu.minn.auth.impl.ui.model.AuthIntent
import ru.kpfu.minn.core.common.di.DependenciesContainer

@Composable
fun AuthScreen(
    onRegisterClicked: () -> Unit
) {
    val dependencies = (LocalContext.current as DependenciesContainer).getDependencies(AuthDependencies::class.java)
    val viewModel: AuthViewModel = viewModel {
        AuthViewModel(
            dependencies = dependencies,
            onRegisterClick = onRegisterClicked,
        )
    }
    val state by viewModel.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.actionFlow.collect { action ->
            when(action) {
                is AuthAction.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = action.message)
                }
            }
        }
    }

    ObserveState(
        state = state,
        onEmailChange = { viewModel.handleIntent(AuthIntent.OnEmailChanged(it)) },
        onPasswordChange = { viewModel.handleIntent(AuthIntent.OnPasswordChanged(it)) },
        onSubmitClick = { viewModel.handleIntent(AuthIntent.OnSubmitClick) },
        onRegisterClick = { viewModel.handleIntent(AuthIntent.OnRegisterClick) },
    )
    SnackbarHost(hostState = snackbarHostState)
}