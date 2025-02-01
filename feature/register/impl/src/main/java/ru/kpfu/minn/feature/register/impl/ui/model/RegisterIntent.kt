package ru.kpfu.minn.feature.register.impl.ui.model

sealed interface RegisterIntent {
    data object onSignInClicked: RegisterIntent
    data object onSubmitClicked: RegisterIntent
    data class onEmailChanged(val email: String): RegisterIntent
    data class onPasswordChanged(val password: String): RegisterIntent
    data class onPasswordRetypeChanged(val password: String): RegisterIntent
    data class onUsernameChanged(val username: String): RegisterIntent
}