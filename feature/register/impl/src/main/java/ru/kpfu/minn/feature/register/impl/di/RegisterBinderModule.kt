package ru.kpfu.minn.feature.register.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.feature.register.api.repository.UserRepository
import ru.kpfu.minn.feature.register.api.usecase.RegisterUserUseCase
import ru.kpfu.minn.feature.register.impl.repository.UserRepositoryImpl
import ru.kpfu.minn.feature.register.impl.usecase.RegisterUserUseCaseImpl

@Module
internal interface RegisterBinderModule {

    @Binds
    fun bindRegisterUserUseCaseToImpl(registerUserUseCaseImpl: RegisterUserUseCaseImpl): RegisterUserUseCase

    @Binds
    fun bindUserRepositoryToImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository

}