package club.anifox.android.di

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache5.Apache5
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun commonModule(enableNetworkLogging: Boolean, context: Context) = module {
    single { createJson() }
    single { createHttpClient(get(), context, get(), enableNetworkLogs = enableNetworkLogging) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob() ) }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = false
}

fun createHttpClient(httpClientEngine: HttpClientEngine, context: Any, json: Json, enableNetworkLogs: Boolean) = HttpClient(
    Apache5
) {
    engine {
//        sslContext = getSSLContext(context)
    }
    install(ContentNegotiation) {
        json(json)
    }
    install(HttpCache)
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
    install(HttpTimeout){
        requestTimeoutMillis = 300000
    }
}
