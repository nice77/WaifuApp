package ru.kpfu.minn.core.data.impl.firebase.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.core.data.api.messaging.ChatsDatasource
import ru.kpfu.minn.core.data.api.messaging.MessagesDatasource
import ru.kpfu.minn.core.data.api.messaging.CloudMessagingService
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.core.data.impl.firebase.favorites.FavoritesDatasourceImpl
import ru.kpfu.minn.core.data.impl.firebase.messaging.ChatsDatasourceImpl
import ru.kpfu.minn.core.data.impl.firebase.messaging.MessagesDatasourceImpl
import ru.kpfu.minn.core.data.impl.firebase.messaging.CloudMessagingServiceImpl
import ru.kpfu.minn.core.data.impl.firebase.users.UserDatasourceImpl
import ru.kpfu.minn.core.data.impl.firebase.users.UserServiceImpl

@Module
internal interface FirebaseBindsModule {

    @Binds
    fun bindUserServiceToImpl(userServiceImpl: UserServiceImpl): UserService

    @Binds
    fun bindUserDatasourceToImpl(userDatasourceImpl: UserDatasourceImpl): UserDatasource

    @Binds
    fun bindFavoritesDatasourceToImpl(favoritesDatasourceImpl: FavoritesDatasourceImpl): FavoritesDatasource

    @Binds
    fun bindMessagingServiceToImpl(messagingServiceImpl: CloudMessagingServiceImpl): CloudMessagingService

    @Binds
    fun bindMessagesDatasourceToImpl(messagesDatasourceImpl: MessagesDatasourceImpl): MessagesDatasource

    @Binds
    fun bindChatsDatasourceToImpl(chatsDatasourceImpl: ChatsDatasourceImpl): ChatsDatasource
}