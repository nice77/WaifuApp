package ru.kpfu.minn.feature.register.impl.ui.model

data class RegisterState(
    val email: String = "nice@gmail.com",
    val password: String = "1234Nice@",
    val passwordRetype: String = "1234Nice@",
    val username: String = "nice",
    val isLoading: Boolean = false,
    val isRetypeEditedOnce: Boolean = false,
    val arePasswordsEqual: Boolean = true,
    val isEmailValid: Boolean = true,
)
