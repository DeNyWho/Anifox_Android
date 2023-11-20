package club.anifox.android.di

import android.content.Context
import club.anifox.android.BuildConfig
import club.anifox.android.core.SslSettings
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
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule(applicationContext: Context) = module {
    single { createJson() }
    single { createHttpClient(get(), applicationContext) }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = false
}

fun createHttpClient(json: Json, applicationContext: Context) = HttpClient(
    OkHttp
) {
    engine {
//        addInterceptor(authInterceptor)
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
    if(BuildConfig.DEBUG) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 300000
    }
}
