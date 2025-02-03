package ru.kpfu.minn.feature.profile.impl.ui.mvi

sealed interface ProfileAction {
    data class ShowMessage(val message: String): ProfileAction
}