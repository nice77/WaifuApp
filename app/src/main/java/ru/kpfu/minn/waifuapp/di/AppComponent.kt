package ru.kpfu.minn.waifuapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.core.common.di.AppScope
import ru.kpfu.minn.core.data.impl.firebase.di.FirebaseModule
import ru.kpfu.minn.feature.profile.impl.di.ProfileDependencies
import ru.kpfu.minn.feature.register.impl.di.RegisterDependencies
import ru.kpfu.minn.waifuapp.MainActivity


@AppScope
@Component(modules = [
    FeatureDependenciesModule::class,
    FirebaseModule::class,
])
interface AppComponent: AuthDependencies, RegisterDependencies, ProfileDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx: Context): AppComponent
    }

    fun inject(activity: MainActivity)

}