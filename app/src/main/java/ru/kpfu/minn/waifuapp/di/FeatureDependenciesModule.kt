package ru.kpfu.minn.waifuapp.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.core.common.di.ComponentDependencies
import ru.kpfu.minn.core.common.di.ComponentDependenciesKey
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.profile.impl.di.ProfileDependencies
import ru.kpfu.minn.feature.register.impl.di.RegisterDependencies
import ru.kpfu.minn.feature.search.impl.di.SearchDependencies

@Module
interface FeatureDependenciesModule {

    @Multibinds
    fun dependenciesMap(): DependenciesMap

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AuthDependencies::class)
    fun bindAuthDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(RegisterDependencies::class)
    fun bindRegisterDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(ProfileDependencies::class)
    fun bindProfileDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SearchDependencies::class)
    fun bindSearchDependencies(appComponent: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MessagingDependencies::class)
    fun bindMessagingDependencies(appComponent: AppComponent): ComponentDependencies
}
