package club.anifox.android.data.remote.account

import club.anifox.android.core.Endpoints
import club.anifox.android.data.remote.safeApiCall
import club.anifox.android.domain.model.common.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.component.KoinComponent

class AccountService(
    private val client: HttpClient,
) : KoinComponent {

    suspend fun changeUserNickName(nickname: String): Resource<Unit> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.account}${Endpoints.nickname}"
            }
            parameter("nickname", nickname)
        }
        println("WWW ZXC")

        return safeApiCall<Unit>(client, request)
    }
}
