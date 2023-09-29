package club.anifox.android.data.remote.auth

import club.anifox.android.core.Endpoints
import club.anifox.android.data.remote.safeApiCall
import club.anifox.android.domain.model.common.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.component.KoinComponent

class AuthService(
    private val client: HttpClient
): KoinComponent {

    suspend fun authentication(userIdentifier: String, password: String): Resource<Unit> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.auth}${Endpoints.authentication}"
            }
        }

        return safeApiCall<Unit>(client, request)
    }

    suspend fun registration(userIdentifier: String, password: String): Resource<Unit> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.auth}${Endpoints.authentication}"
            }
        }

        return safeApiCall<Unit>(client, request)
    }

    suspend fun refreshAccessToken(refreshToken: String): Resource<Unit> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.auth}${Endpoints.authentication}"
            }
        }

        return safeApiCall<Unit>(client, request)
    }


}