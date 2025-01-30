package ru.kpfu.minn.waifuapp.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.di.ComponentDependenciesKey

@Module
interface FeatureDependenciesModule {

    @Multibinds
    fun dependenciesMap(): DependenciesMap

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AuthDependencies::class)
    fun bindsUploadPostDeps(appComponent: AppComponent): ComponentDependencies
}
