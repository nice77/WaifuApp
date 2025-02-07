package ru.kpfu.minn.feature.messaging.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.api.usecase.AddChatUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatIdByOtherUserUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetChatsUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetCurrentUserUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetMessagesUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.GetUserDetailsUseCase
import ru.kpfu.minn.feature.messaging.api.usecase.SendMessageUseCase
import ru.kpfu.minn.feature.messaging.impl.repository.ChatsRepositoryImpl
import ru.kpfu.minn.feature.messaging.impl.repository.MessagesRepositoryImpl
import ru.kpfu.minn.feature.messaging.impl.repository.UsersRepositoryImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.AddChatUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetChatIdByOtherUserUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetChatsUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetCurrentUserUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetMessagesUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetUserDetailsUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.SendMessageUseCaseImpl


@Module
internal interface MessagingBinderModule {

    @Binds
    fun bindMessagesRepositoryToImpl(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    fun bindSendMessageUseCaseToImpl(sendMessageUseCaseImpl: SendMessageUseCaseImpl): SendMessageUseCase

    @Binds
    fun bindChatRepositoryToImpl(chatsRepositoryImpl: ChatsRepositoryImpl): ChatsRepository

    @Binds
    fun bindGetChatsUseCaseToImpl(chatsUseCaseImpl: GetChatsUseCaseImpl): GetChatsUseCase

    @Binds
    fun bindUserRepositoryToImpl(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository

    @Binds
    fun bindGetUserDetailsUseCaseToImpl(getUserDetailsUseCaseImpl: GetUserDetailsUseCaseImpl): GetUserDetailsUseCase

    @Binds
    fun bindGetMessagesUseCaseToImpl(getMessagesUseCaseImpl: GetMessagesUseCaseImpl): GetMessagesUseCase

    @Binds
    fun bindGetCurrentUserUseCaseToImpl(getCurrentUserUseCaseImpl: GetCurrentUserUseCaseImpl): GetCurrentUserUseCase

    @Binds
    fun bindAddChatUseCaseToImpl(addChatUseCaseImpl: AddChatUseCaseImpl): AddChatUseCase

    @Binds
    fun bindGetChatIdByOtherUserUseCaseToImpl(getChatIdByOtherUserUseCaseImpl: GetChatIdByOtherUserUseCaseImpl): GetChatIdByOtherUserUseCase

}