package club.anifox.android.core

import android.content.Context
import club.anifox.android.BuildConfig
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object SslSettings {
    private fun getKeyStore(context: Context): KeyStore {
        val certificateFileName = BuildConfig.cert_path

        val certificateFactory = CertificateFactory.getInstance("X.509")
        val inputStream: InputStream = context.assets.open(certificateFileName)
        val certificate = certificateFactory.generateCertificate(inputStream)
        inputStream.close()

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null)
        keyStore.setCertificateEntry(BuildConfig.cert_alias, certificate)
        return keyStore
    }

    private fun getTrustManagerFactory(context: Context): TrustManagerFactory? {
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(getKeyStore(context))
        return trustManagerFactory
    }

    fun getSslContext(context: Context): SSLContext? {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, getTrustManagerFactory(context)?.trustManagers, null)
        return sslContext
    }

    fun getTrustManager(context: Context): X509TrustManager {
        return getTrustManagerFactory(context)?.trustManagers?.first { it is X509TrustManager } as X509TrustManager
    }
}
