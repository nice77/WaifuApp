package ru.kpfu.minn.auth.impl.ui.model

sealed interface AuthIntent {
    data object OnSubmitClick: AuthIntent
    data object OnRegisterClick: AuthIntent
    data class OnEmailChanged(val email: String): AuthIntent
    data class OnPasswordChanged(val password: String): AuthIntent
}