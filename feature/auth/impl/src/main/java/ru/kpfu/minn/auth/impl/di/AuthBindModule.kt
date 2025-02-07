package ru.kpfu.minn.auth.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.auth.api.repository.CloudMessagingRepository
import ru.kpfu.minn.auth.api.repository.UserRepository
import ru.kpfu.minn.auth.api.usecase.AuthorizeUserUseCase
import ru.kpfu.minn.auth.api.usecase.RetrieveFCMTokenUseCase
import ru.kpfu.minn.auth.impl.repository.CloudMessagingRepositoryImpl
import ru.kpfu.minn.auth.impl.repository.UserRepositoryImpl
import ru.kpfu.minn.auth.impl.usecase.AuthorizeUserUseCaseImpl
import ru.kpfu.minn.auth.impl.usecase.RetrieveFCMTokenUseCaseImpl

@Module
internal interface AuthBindModule {

    @Binds
    fun bindAuthUserUseCaseToImpl(authorizeUserUseCaseImpl: AuthorizeUserUseCaseImpl): AuthorizeUserUseCase

    @Binds
    fun bindRetrieveFCMTokenUseCaseToImpl(retrieveFCMTokenUseCaseImpl: RetrieveFCMTokenUseCaseImpl): RetrieveFCMTokenUseCase

    @Binds
    fun bindCloudMessagingRepositoryToImpl(cloudMessagingRepositoryImpl: CloudMessagingRepositoryImpl): CloudMessagingRepository

    @Binds
    fun bindUserRepositoryToImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
