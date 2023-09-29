package club.anifox.android.data.remote.anime

import club.anifox.android.core.Endpoints
import club.anifox.android.data.remote.safeApiCall
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.AnimeUsersStatusDto
import club.anifox.android.domain.model.dto.anime.detail.AnimeDetailDto
import club.anifox.android.domain.model.dto.anime.light.AnimeLightDto
import club.anifox.android.domain.model.dto.anime.media.AnimeMediaDto
import club.anifox.android.domain.model.dto.anime.related.AnimeRelatedDto
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
    ): Resource<List<AnimeLightDto>> {
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

        return safeApiCall<List<AnimeLightDto>>(client, request)
    }

    suspend fun getAnimeScreenshots(url: String): Resource<List<String>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}${Endpoints.screenShots}"
            }
        }

        return safeApiCall<List<String>>(client, request)
    }

    suspend fun getAnimeRelated(url: String): Resource<List<AnimeRelatedDto>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}${Endpoints.related}"
            }
        }

        return safeApiCall<List<AnimeRelatedDto>>(client, request)
    }

    suspend fun getAnimeSimilar(url: String): Resource<List<AnimeLightDto>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}${Endpoints.similar}"
            }
        }

        return safeApiCall<List<AnimeLightDto>>(client, request)
    }

    suspend fun getAnimeUsersStatus(url: String): Resource<AnimeUsersStatusDto> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}${Endpoints.status}"
            }
        }

        return safeApiCall<AnimeUsersStatusDto>(client, request)
    }

    suspend fun getAnimeDetails(url: String): Resource<AnimeDetailDto> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}"
            }
        }

        return safeApiCall<AnimeDetailDto>(client, request)
    }

    suspend fun getAnimeMedia(url: String): Resource<List<AnimeMediaDto>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = Endpoints.domain
                encodedPath = "${Endpoints.api}${Endpoints.anime}${url}${Endpoints.media}"
            }
        }

        return safeApiCall<List<AnimeMediaDto>>(client, request)
    }

}