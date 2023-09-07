package club.anifox.android.data.remote

import club.anifox.android.core.Endpoints
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.AnimeLightDto
import club.anifox.android.domain.model.response.ServiceResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.component.KoinComponent

class AnimeService(
    private val client: HttpClient
): KoinComponent {

    suspend fun getAnime(
        pageNum: Int,
        pageSize: Int,
        order: String?,
        status: String?,
        genres: List<String>?,
        searchQuery: String?,
        season: String?,
        ratingMpa: String?,
        minimalAge: String?,
        type: String?,
        year: Int?
    ): Resource<ServiceResponse<AnimeLightDto>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}"
                parameter("pageNum", pageNum)
                parameter("pageSize", pageSize)
                if(order != null) parameter("order", order)
                if(season != null) parameter("season", season)
                if(ratingMpa != null) parameter("ratingMpa", ratingMpa)
                if(minimalAge != null) parameter("minimalAge", minimalAge)
                if(year != null) parameter("year", year)
                if(type != null) parameter("type", type)
                if(status != null) if(status.length > 4) parameter("status", status)
                if(genres!= null) if(genres.isNotEmpty()) parameter("genres", genres)
                if(searchQuery!= null) if(searchQuery.length > 1) parameter("searchQuery", searchQuery)
            }
        }

        return safeApiCall<ServiceResponse<AnimeLightDto>>(client, request)
    }

}