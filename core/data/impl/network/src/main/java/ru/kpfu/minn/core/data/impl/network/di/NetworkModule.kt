package ru.kpfu.minn.core.data.impl.network.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module(includes = [NetworkBinderModule::class])
class NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = true
                }
            )
        }
    }

    @Provides
    @DefaultUrl
    fun provideBaseUrl(): String = "https://api.waifu.im/"

}