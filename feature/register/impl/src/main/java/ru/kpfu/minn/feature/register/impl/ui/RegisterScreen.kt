package ru.kpfu.minn.feature.register.impl.ui

import android.util.Log
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kpfu.minn.core.common.di.DependenciesContainer
import ru.kpfu.minn.feature.register.impl.di.RegisterDependencies
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterAction
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterIntent

@Composable
fun RegisterScreen(
    onSignInClicked: () -> Unit
) {
    val dependencies = (LocalContext.current as DependenciesContainer).getDependencies(RegisterDependencies::class.java)
    val viewModel = viewModel {
        RegisterViewModel(dependencies, onSignInClicked)
    }
    val state by viewModel.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.actionFlow.collect { action ->
            when(action) {
                is RegisterAction.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = action.message)
                }
            }
        }
    }
    ObserveState(
        state = state,
        onEmailChanged = { viewModel.handleIntent(RegisterIntent.onEmailChanged(it)) },
        onPasswordChanged = { viewModel.handleIntent(RegisterIntent.onPasswordChanged(it)) },
        onPasswordRetypeChanged = { viewModel.handleIntent(RegisterIntent.onPasswordRetypeChanged(it)) },
        onUsernameChanged = { viewModel.handleIntent(RegisterIntent.onUsernameChanged(it)) },
        onSubmitClicked = { viewModel.handleIntent(RegisterIntent.onSubmitClicked) },
        onSignInClicked = { viewModel.handleIntent(RegisterIntent.onSignInClicked) },
    )
    SnackbarHost(hostState = snackbarHostState)
}