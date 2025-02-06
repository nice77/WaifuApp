package ru.kpfu.minn.feature.search.impl.ui.mvi

sealed interface SearchAction {

    data class ShowMessage(val message: String): SearchAction

    data class PerformNavigation(val uid: String): SearchAction

}