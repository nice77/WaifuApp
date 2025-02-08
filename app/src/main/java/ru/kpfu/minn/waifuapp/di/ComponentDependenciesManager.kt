package ru.kpfu.minn.waifuapp.di

import ru.kpfu.minn.core.common.di.AppScope
import ru.kpfu.minn.core.common.di.ComponentDependencies
import javax.inject.Inject

typealias DependenciesMap = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

@AppScope
class ComponentDependenciesManager @Inject constructor(
    private val dependenciesMap: DependenciesMap
) {
    @Suppress("UNCHECKED_CAST")
    fun <T : ComponentDependencies> getDependencies(key: Class<T>): T =
        (dependenciesMap[key] as T?)
            ?: throw IllegalStateException("Missing dependencies for key $key")
}
