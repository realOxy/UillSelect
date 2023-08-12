package com.oxy.uillselect.data.api

import de.jensklingenberg.ktorfit.Ktorfit

object KtorfitApiFactory: ApiFactory {
    private val client by lazy {
        Ktorfit.Builder()
            .baseUrl("https://localhost:8080/uillselect")
            .build()
    }

    override fun <T> createApi(): T = client.create()
}
