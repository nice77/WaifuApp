package ru.kpfu.minn.feature.messaging.impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(
    includes = [MessagingBinderModule::class],
)
class MessagingModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}