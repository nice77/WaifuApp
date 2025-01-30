package ru.kpfu.minn.core.common.di

interface DependenciesContainer {

    fun <T : ComponentDependencies> getDependencies(key: Class<T>): T

}
