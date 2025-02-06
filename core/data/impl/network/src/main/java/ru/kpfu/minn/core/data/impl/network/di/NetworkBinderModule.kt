package ru.kpfu.minn.core.data.impl.network.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.core.data.api.waifuimages.CustomHttpClient
import ru.kpfu.minn.core.data.api.waifuimages.datasource.WaifuImagesDatasource
import ru.kpfu.minn.core.data.impl.network.CustomHttpClientImpl
import ru.kpfu.minn.core.data.impl.network.waifuimages.WaifuImagesDatasourceImpl

@Module
internal interface NetworkBinderModule {

    @Binds
    fun bindWaifuApiToImpl(waifuApiImpl: WaifuImagesDatasourceImpl): WaifuImagesDatasource

    @Binds
    fun bindCustomHttpClientToImpl(customHttpClientImpl: CustomHttpClientImpl): CustomHttpClient

}