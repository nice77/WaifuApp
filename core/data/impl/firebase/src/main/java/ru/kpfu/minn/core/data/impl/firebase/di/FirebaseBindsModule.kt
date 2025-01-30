package ru.kpfu.minn.core.data.impl.firebase.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.core.common.di.AppScope
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.impl.firebase.UserServiceImpl

@Module
internal interface FirebaseBindsModule {

    @Binds
    fun bindUserServiceToImpl(userServiceImpl: UserServiceImpl): UserService

}