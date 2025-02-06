package ru.kpfu.minn.feature.search.impl.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(
    includes = [SearchBinderModule::class]
)
class SearchModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}