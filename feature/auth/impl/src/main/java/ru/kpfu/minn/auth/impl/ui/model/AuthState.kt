package ru.kpfu.minn.auth.impl.ui.model

data class AuthState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
)