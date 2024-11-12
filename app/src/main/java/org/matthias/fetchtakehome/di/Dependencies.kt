package org.matthias.fetchtakehome.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.matthias.fetchtakehome.network.DefaultHiringService
import org.matthias.fetchtakehome.network.HiringService

object Dependencies {
    private val json by lazy {
        Json { ignoreUnknownKeys = true }
    }

    private val httpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    val hiringService: HiringService by lazy {
        DefaultHiringService(httpClient)
    }
}