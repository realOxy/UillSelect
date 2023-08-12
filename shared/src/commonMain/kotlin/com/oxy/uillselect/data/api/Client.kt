package com.oxy.uillselect.data.api

import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

private const val BASE_URL = "https://getman.cn/mock/"

val ktorfit = ktorfit {
    baseUrl(BASE_URL)
    httpClient {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Any
            )
        }
    }
    converterFactories(
        FlowConverterFactory(),
        CallConverterFactory()
    )
}