package club.anifox.android.di

import android.content.Context
import club.anifox.android.core.SslSettings
import club.anifox.android.data.remote.AuthInterceptor
import club.anifox.android.preferences.PreferencesDataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun commonModule(enableNetworkLogging: Boolean, applicationContext: Context) = module {
    single { createJson() }
    single { createHttpClient(get(), enableNetworkLogging, applicationContext, get()) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
    single { PreferencesDataStore(applicationContext) }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = false
}

fun createHttpClient(json: Json, enableNetworkLogging: Boolean, applicationContext: Context, authInterceptor: AuthInterceptor) = HttpClient(OkHttp) {
    engine {
        addInterceptor(authInterceptor)
        config {
            sslSocketFactory(SslSettings.getSslContext(applicationContext)!!.socketFactory, SslSettings.getTrustManager(applicationContext))
        }
    }
    install(ContentNegotiation) {
        json(json)
    }
    defaultRequest {
        header("Content-Type", "application/json")
    }
    install(HttpCookies)
    install(HttpCache)
    if (enableNetworkLogging) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 300000
    }
}
