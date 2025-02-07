package ru.kpfu.minn.feature.messaging.impl.di

import dagger.Component
import dagger.Component.Factory
import ru.kpfu.minn.feature.messaging.impl.repository.paging.MessagesPagingSource
import ru.kpfu.minn.feature.messaging.impl.ui.chatsscreen.ChatsViewModel
import ru.kpfu.minn.feature.messaging.impl.ui.currentchat.CurrentChatViewModel


@Component(
    modules = [MessagingModule::class],
    dependencies = [MessagingDependencies::class]
)
internal interface MessagingComponent: MessagingDependencies {

    @Factory
    interface MessagingComponentFactory {
        fun create(dependencies: MessagingDependencies): MessagingComponent
    }

    fun messagesPagingSourceFactory(): MessagesPagingSource.MessagesPagingSourceFactory

    fun inject(viewModel: ChatsViewModel)

    fun inject(viewModel: CurrentChatViewModel)
}