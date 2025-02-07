package ru.kpfu.minn.waifuapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kpfu.minn.auth.impl.di.AuthDependencies
import ru.kpfu.minn.core.common.di.AppScope
import ru.kpfu.minn.core.data.impl.firebase.di.FirebaseModule
import ru.kpfu.minn.core.data.impl.network.di.NetworkModule
import ru.kpfu.minn.feature.messaging.impl.di.MessagingDependencies
import ru.kpfu.minn.feature.profile.impl.di.ProfileDependencies
import ru.kpfu.minn.feature.register.impl.di.RegisterDependencies
import ru.kpfu.minn.feature.search.impl.di.SearchDependencies
import ru.kpfu.minn.waifuapp.MainActivity
import ru.kpfu.minn.waifuapp.MessagingService


@AppScope
@Component(modules = [
    FeatureDependenciesModule::class,
    FirebaseModule::class,
    NetworkModule::class,
])
interface AppComponent:
    AuthDependencies,
    RegisterDependencies,
    ProfileDependencies,
    SearchDependencies,
    MessagingDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx: Context): AppComponent
    }

    fun inject(messagingService: MessagingService)

    fun inject(activity: MainActivity)

}