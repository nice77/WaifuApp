package ru.kpfu.minn.core.data.impl.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import ru.kpfu.minn.core.data.api.waifuimages.CustomHttpClient
import javax.inject.Inject

internal class CustomHttpClientImpl @Inject constructor(
    private val _httpClient: HttpClient,
): CustomHttpClient {

    override suspend fun get(
        urlString: String,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit,
    ): HttpResponse = _httpClient.get(urlString, httpRequestBuilder)

    override suspend fun get(
        httpRequestBuilder: HttpRequestBuilder.() -> Unit,
    ): HttpResponse =
        _httpClient.get(httpRequestBuilder)

    override suspend fun post(
        urlString: String,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit
    ): HttpResponse = _httpClient.post(urlString = urlString, block = httpRequestBuilder)

    override suspend fun post(httpRequestBuilder: HttpRequestBuilder.() -> Unit): HttpResponse =
        _httpClient.post(httpRequestBuilder)

}