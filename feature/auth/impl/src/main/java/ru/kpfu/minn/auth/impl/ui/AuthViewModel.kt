package ru.kpfu.minn.auth.impl.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.minn.auth.api.usecase.AuthorizeUserUseCase
import ru.kpfu.minn.auth.api.usecase.RetrieveFCMTokenUseCase
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.auth.impl.di.DaggerAuthComponent
import ru.kpfu.minn.auth.impl.ui.model.AuthAction
import ru.kpfu.minn.auth.impl.ui.model.AuthIntent
import ru.kpfu.minn.auth.impl.ui.model.AuthState
import ru.kpfu.minn.core.common.BaseViewModel
import javax.inject.Inject

class AuthViewModel(
    dependencies: AuthDependencies,
    private val onAuthSuccess: () -> Unit,
    private val onRegisterClick: () -> Unit,
): BaseViewModel<AuthAction, AuthState, AuthIntent>(initialState = AuthState()) {

    init {
        val component = DaggerAuthComponent
            .factory()
            .create(dependencies)
        component.inject(this@AuthViewModel)
    }

    @Inject
    lateinit var authorizeUserUseCase: AuthorizeUserUseCase

    @Inject
    lateinit var retrieveFCMTokenUseCase: RetrieveFCMTokenUseCase

    override fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.OnEmailChanged -> {
                _stateFlow.value = _stateFlow.value.copy(email = intent.email.trim())
            }
            is AuthIntent.OnPasswordChanged -> {
                _stateFlow.value = _stateFlow.value.copy(password = intent.password.trim())
            }
            AuthIntent.OnRegisterClick -> onRegisterClick()
            AuthIntent.OnSubmitClick -> logIn()
        }
    }

    private fun logIn() {
        _stateFlow.value = _stateFlow.value.copy(isLoading = true)
        viewModelScope.launch {
            _stateFlow.value.run {
                authorizeUserUseCase(email = email, password = password).onSuccess {
                    _stateFlow.value = _stateFlow.value.copy(isLoading = false)
                    retrieveFCMTokenUseCase().onSuccess {
                        onAuthSuccess()
                    }.onFailure {
                        emitErrorMessage(it)
                    }
                }.onFailure {
                    emitErrorMessage(it)
                }
            }
        }
    }

    private suspend fun emitErrorMessage(e: Throwable) {
        _stateFlow.value = _stateFlow.value.copy(isLoading = false)
        _actionFlow.emit(AuthAction.ShowMessage(message = e.message ?: "Unknown Exception"))
    }
}