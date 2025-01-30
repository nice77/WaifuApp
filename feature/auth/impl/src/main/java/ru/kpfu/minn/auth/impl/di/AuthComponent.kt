package ru.kpfu.minn.auth.impl.di

import dagger.Component
import dagger.Component.Factory
import ru.kpfu.minn.auth.impl.ui.AuthViewModel
import ru.kpfu.minn.core.common.di.FeatureScope

@Component(
    modules = [AuthBindModule::class, AuthModule::class],
    dependencies = [AuthDependencies::class],
)
@FeatureScope
internal interface AuthComponent: AuthDependencies {

    @Factory
    interface AuthComponentFactory {
        fun create(authDependencies: AuthDependencies): AuthComponent
    }

    fun inject(viewModel: AuthViewModel)
}
