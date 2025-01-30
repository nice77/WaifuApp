package ru.kpfu.minn.waifuapp.di

import dagger.Component
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.core.common.di.AppScope
import ru.kpfu.minn.core.data.impl.firebase.di.FirebaseModule
import ru.kpfu.minn.waifuapp.MainActivity


@AppScope
@Component(modules = [
    FeatureDependenciesModule::class,
    FirebaseModule::class,
])
interface AppComponent: AuthDependencies {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)

}