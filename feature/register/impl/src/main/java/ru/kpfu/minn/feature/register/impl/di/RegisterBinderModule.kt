package ru.kpfu.minn.feature.register.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.feature.register.api.repository.CloudMessagingRepository
import ru.kpfu.minn.feature.register.api.repository.UserRepository
import ru.kpfu.minn.feature.register.api.usecase.RegisterUserUseCase
import ru.kpfu.minn.feature.register.api.usecase.RetrieveFCMTokenUseCase
import ru.kpfu.minn.feature.register.impl.repository.CloudMessagingRepositoryImpl
import ru.kpfu.minn.feature.register.impl.repository.UserRepositoryImpl
import ru.kpfu.minn.feature.register.impl.usecase.RegisterUserUseCaseImpl
import ru.kpfu.minn.feature.register.impl.usecase.RetrieveFCMTokenUseCaseImpl

@Module
internal interface RegisterBinderModule {

    @Binds
    fun bindRegisterUserUseCaseToImpl(registerUserUseCaseImpl: RegisterUserUseCaseImpl): RegisterUserUseCase

    @Binds
    fun bindUserRepositoryToImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRetrieveFCMTokenUseCaseToImpl(setupFCMTokenUseCaseImpl: RetrieveFCMTokenUseCaseImpl): RetrieveFCMTokenUseCase

    @Binds
    fun bindCloudMessagingRepositoryToImpl(cloudMessagingRepositoryImpl: CloudMessagingRepositoryImpl): CloudMessagingRepository
}