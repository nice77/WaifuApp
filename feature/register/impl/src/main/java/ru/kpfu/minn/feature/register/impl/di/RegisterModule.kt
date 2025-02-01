package ru.kpfu.minn.feature.register.impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [RegisterBinderModule::class])
class RegisterModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}