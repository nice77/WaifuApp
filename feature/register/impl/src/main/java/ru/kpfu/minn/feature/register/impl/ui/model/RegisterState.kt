package ru.kpfu.minn.feature.register.impl.ui.model

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val passwordRetype: String = "",
    val username: String = "",
    val isLoading: Boolean = false,
    val isRetypeEditedOnce: Boolean = false,
    val arePasswordsEqual: Boolean = true,
    val isEmailValid: Boolean = true,
)
