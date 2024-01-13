package club.anifox.android.data.network.service

import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent

class AnimeService(
    client: HttpClient
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
        year: Int?,
    ) {

    }
}