package club.anifox.android.data.remote.auth

import club.anifox.android.core.Endpoints
import club.anifox.android.data.remote.safeApiCall
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.auth.AuthenticationDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.component.KoinComponent

class AuthService(
    private val client: HttpClient,
) : KoinComponent {

    suspend fun authentication(authenticationDto: AuthenticationDto): Resource<Unit> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post

            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.auth}${Endpoints.authentication}"
            }

            setBody(authenticationDto)
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
