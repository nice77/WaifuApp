package ru.kpfu.minn.feature.register.impl.ui

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.minn.core.common.BaseViewModel
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.StringResProvider
import ru.kpfu.minn.feature.register.api.usecase.RegisterUserUseCase
import ru.kpfu.minn.feature.register.api.usecase.RetrieveFCMTokenUseCase
import ru.kpfu.minn.feature.register.impl.R
import ru.kpfu.minn.feature.register.impl.di.DaggerRegisterComponent
import ru.kpfu.minn.feature.register.impl.di.RegisterDependencies
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterAction
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterIntent
import ru.kpfu.minn.feature.register.impl.ui.model.RegisterState
import javax.inject.Inject

internal class RegisterViewModel(
    dependencies: RegisterDependencies,
    private val onSignInClicked: () -> Unit,
    private val onRegisterSuccess: () -> Unit,
): BaseViewModel<RegisterAction, RegisterState, RegisterIntent>(
    initialState = RegisterState(),
) {

    init {
        val component = DaggerRegisterComponent
            .factory()
            .create(dependencies)
        component.inject(this@RegisterViewModel)
    }

    @Inject
    lateinit var registerUserUseCase: RegisterUserUseCase

    @Inject
    lateinit var stringResProvider: StringResProvider

    @Inject
    lateinit var retrieveFCMTokenUseCase: RetrieveFCMTokenUseCase

    override fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.onUsernameChanged -> { _stateFlow.value = _stateFlow.value.copy(username = intent.username) }
            is RegisterIntent.onEmailChanged -> {
                _stateFlow.value = _stateFlow.value.copy(email = intent.email)
                isEmailValid()
            }
            is RegisterIntent.onPasswordChanged -> {
                _stateFlow.value = _stateFlow.value.copy(password = intent.password)
                if (_stateFlow.value.isRetypeEditedOnce) {
                    _stateFlow.value = _stateFlow.value.copy(
                        arePasswordsEqual = (_stateFlow.value.password == _stateFlow.value.passwordRetype),
                    )
                }
            }
            is RegisterIntent.onPasswordRetypeChanged -> {
                _stateFlow.value = _stateFlow.value.copy(
                    passwordRetype = intent.password,
                    arePasswordsEqual = (intent.password == _stateFlow.value.password),
                    isRetypeEditedOnce = true,
                )
            }
            RegisterIntent.onSignInClicked -> onSignInClicked()
            RegisterIntent.onSubmitClicked -> register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            _stateFlow.value = _stateFlow.value.copy(isLoading = true)
            _stateFlow.value.run {
                retrieveFCMTokenUseCase().onSuccess { token ->
                    registerUserUseCase(email, password, username, token).onSuccess {
                        _stateFlow.value = _stateFlow.value.copy(
                            isLoading = false,
                        )
                        onRegisterSuccess()
                    }.onFailure { emitErrorMessage(it) }
                }.onFailure {
                    emitErrorMessage(it)
                }
            }
        }
    }

    private suspend fun emitErrorMessage(e: Throwable) {
        _stateFlow.value = _stateFlow.value.copy(
            isLoading = false,
        )
        _actionFlow.emit(
            RegisterAction.ShowMessage(
                e.message ?: stringResProvider.getString(R.string.unknown_error)
            )
        )
    }

    private fun isEmailValid() {
        _stateFlow.value = _stateFlow.value.copy(
            isEmailValid = _stateFlow.value.email.matches(Regex(Patterns.EMAIL_ADDRESS.pattern())),
        )
    }
}
