package ru.kpfu.minn.auth.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.auth.api.router.AuthFeatureRoute
import ru.kpfu.minn.auth.api.usecase.AuthorizeUserUseCase
import ru.kpfu.minn.auth.impl.router.AuthFeatureRouteImpl
import ru.kpfu.minn.auth.impl.usecase.AuthorizeUserUseCaseImpl

@Module
internal interface AuthBindModule {

    @Binds
    fun bindAuthUserUseCaseToImpl(authorizeUserUseCaseImpl: AuthorizeUserUseCaseImpl): AuthorizeUserUseCase

    @Binds
    fun bindAuthFeatureRouteToImpl(authFeatureRouteImpl: AuthFeatureRouteImpl): AuthFeatureRoute

}
