package club.anifox.android.data.network.service

import club.anifox.android.BuildConfig
import club.anifox.android.core.Endpoints
import club.anifox.android.data.network.safeApiCall
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.AnimeType
import club.anifox.android.domain.model.anime.request.FilterEnum
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.AnimeLightDto
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.component.KoinComponent

class AnimeService(
    private val client: HttpClient,
): KoinComponent {

    suspend fun getAnime(
        page: Int,
        limit: Int,
        status: AnimeStatus?,
        genres: List<String>?,
        searchQuery: String?,
        season: AnimeSeason?,
        ratingMpa: String?,
        minimalAge: String?,
        type: AnimeType?,
        year: Int?,
        studio: String?,
        filter: FilterEnum?,
    ): Resource<List<AnimeLightDto>> {
        val request = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.hostname
                encodedPath = "${BuildConfig.api_path}${Endpoints.anime}"
                parameter("page", page)
                parameter("limit", limit)
                if (season != null) parameter("season", season.name)
                if (ratingMpa != null) parameter("rating_mpa", ratingMpa)
                if (minimalAge != null) parameter("age", minimalAge)
                if (year != null) parameter("year", year)
                if (type != null) parameter("type", type.name)
                if (status != null) parameter("status", status.name)
                if (genres != null) if (genres.isNotEmpty()) parameter("genres", genres)
                if (studio != null) parameter("studio", studio)
                if (searchQuery != null) parameter("searchQuery", searchQuery)
                if (filter != null) parameter("filter", filter.name)
            }
        }

        return safeApiCall<List<AnimeLightDto>>(client, request)
    }
}
