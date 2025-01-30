package ru.kpfu.minn.auth.impl.ui.model

sealed interface AuthAction {
    data class ShowMessage(val message: String): AuthAction
}