package ru.kpfu.minn.feature.register.impl.di

import dagger.Component
import dagger.Component.Factory
import ru.kpfu.minn.core.common.di.FeatureScope
import ru.kpfu.minn.feature.register.impl.ui.RegisterViewModel

@FeatureScope
@Component(
    modules = [RegisterModule::class],
    dependencies = [RegisterDependencies::class],
)
internal interface RegisterComponent {

    @Factory
    interface RegisterFactory {
        fun create(dependencies: RegisterDependencies): RegisterComponent
    }

    fun inject(registerViewModel: RegisterViewModel)
}