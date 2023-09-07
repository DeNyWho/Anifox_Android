package club.anifox.android.di

import android.content.Context
import club.anifox.android.preferences.PreferencesDataStore
import io.ktor.client.HttpClient
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
import java.io.InputStream
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

fun commonModule(enableNetworkLogging: Boolean, applicationContext: Context) = module {
    single { createJson() }
    single { createHttpClient(get(), enableNetworkLogging, applicationContext) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob() ) }
    single { PreferencesDataStore(applicationContext) }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = false
}

fun getSSLContext(context: Context): SSLContext {

    val certificateFileName = "ConfigAniFox/config/certs/certificate.crt"

    // Загрузка сертификата из assets
    val certificateFactory = CertificateFactory.getInstance("X.509")
    val inputStream: InputStream = context.assets.open(certificateFileName)
    val certificate = certificateFactory.generateCertificate(inputStream)
    inputStream.close()

    // Создание keystore и добавление сертификата
    val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
    keyStore.load(null)
    keyStore.setCertificateEntry("myalias", certificate)

    // Создание SSL контекста
    val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    trustManagerFactory.init(keyStore)

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustManagerFactory.trustManagers, SecureRandom())

    return sslContext
}

fun createHttpClient(json: Json, enableNetworkLogging: Boolean, applicationContext: Context) = HttpClient(Apache5) {
    engine {
        sslContext = getSSLContext(applicationContext)
    }
    install(ContentNegotiation) {
        json(json)
    }
    install(HttpCache)
    if(enableNetworkLogging) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }
    install(HttpTimeout){
        requestTimeoutMillis = 300000
    }
}
