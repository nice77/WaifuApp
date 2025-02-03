package ru.kpfu.minn.feature.profile.impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(
    includes = [ProfileBinderModule::class]
)
internal class ProfileModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}