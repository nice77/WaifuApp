package ru.kpfu.minn.core.data.api.waifuimages

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

interface CustomHttpClient {

    suspend fun get(
        urlString: String,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): HttpResponse

    suspend fun get(
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): HttpResponse

    suspend fun post(
        urlString: String,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): HttpResponse

    suspend fun post(
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): HttpResponse

}