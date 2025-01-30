package ru.kpfu.minn.auth.impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AuthModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}