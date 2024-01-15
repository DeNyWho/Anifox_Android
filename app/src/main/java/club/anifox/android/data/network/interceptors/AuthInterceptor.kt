package club.anifox.android.data.network.interceptors

import android.content.Context
import club.anifox.android.BuildConfig
import club.anifox.android.core.Endpoints
import club.anifox.android.core.SslSettings
import club.anifox.android.data.datastore.repository.UserDataRepository
import club.anifox.android.domain.model.user.UserSession
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.hc.core5.http.HttpHeaders

class AuthInterceptor(
    private val userDataRepository: UserDataRepository,
    private val applicationContext: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val session = runBlocking {
            userDataRepository.session.firstOrNull()
        }

        val (oldRequest, oldResponse) = makeRequest(chain, session?.accessToken)
        if (oldResponse.code == 401 && session?.refreshToken?.isNotEmpty() == true) {
            val response = refreshToken(session.refreshToken)
            if (response.code == 200) {
                retryRequest(response, oldRequest, chain)
            }
        }

        return oldResponse
    }

    private fun makeRequest(
        chain: Interceptor.Chain,
        accessToken: String?,
    ): Pair<Request, Response> {
        val oldRequest = chain
            .request()
            .newBuilder()
            .addHeader(
                HttpHeaders.AUTHORIZATION,
                "Bearer $accessToken",
            )
            .build()

        val oldResponse = chain.proceed(oldRequest)
        return Pair(oldRequest, oldResponse)
    }

    private fun refreshToken(refreshToken: String): Response {
        val client = OkHttpClient().newBuilder()
            .sslSocketFactory(SslSettings.getSslContext(applicationContext)!!.socketFactory, SslSettings.getTrustManager(applicationContext))
            .build()
        val newRequest = Request.Builder()
            .get()
            .url("https://${BuildConfig.hostname}${BuildConfig.api_path}${Endpoints.auth}${Endpoints.refresh}?refreshToken=$refreshToken")
            .build()
        return client.newCall(newRequest).execute()
    }

    private fun retryRequest(
        response: Response,
        oldRequest: Request,
        chain: Interceptor.Chain,
    ) {
        val cookieHeader = response.headers("Set-Cookie")

        var accessToken: String? = null
        var refreshToken: String? = null

        for (cookie in cookieHeader) {
            if (cookie.startsWith("access_token=")) {
                accessToken = cookie.substringAfter("access_token=").substringBefore(";")
            } else if (cookie.startsWith("refresh_token=")) {
                refreshToken = cookie.substringAfter("refresh_token=").substringBefore(";")
            }
        }

        if (accessToken != null && refreshToken != null) {
            runBlocking {
                userDataRepository.updateSession(session = UserSession(accessToken, refreshToken))

                val newRequestWithAccessToken = oldRequest.newBuilder()
                    .removeHeader(HttpHeaders.AUTHORIZATION)
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                    .build()

                return@runBlocking chain.proceed(newRequestWithAccessToken)
            }
        }
    }
}