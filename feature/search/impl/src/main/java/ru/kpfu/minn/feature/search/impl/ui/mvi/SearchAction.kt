package ru.kpfu.minn.feature.search.impl.ui.mvi

sealed interface SearchAction {

    data class ShowMessage(val message: String): SearchAction

    data class PerformNavigationToProfile(val uid: String): SearchAction

    data class PerformNavigationToChat(val uid: String): SearchAction

}