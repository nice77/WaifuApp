package ru.kpfu.minn.core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<Action, State, Intent>(
    initialState: State
): ViewModel() {

    protected val _stateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<State> = _stateFlow

    protected val _actionFlow: MutableSharedFlow<Action> = MutableSharedFlow()
    val actionFlow: SharedFlow<Action> = _actionFlow

    abstract fun handleIntent(intent: Intent)
}