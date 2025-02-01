package ru.kpfu.minn.feature.register.impl.ui.model

sealed interface RegisterAction {
    data class ShowMessage(val message: String): RegisterAction
}