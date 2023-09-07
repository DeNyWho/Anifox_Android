package club.anifox.android.domain.usecase.anime

import androidx.paging.PagingData
import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity
import club.anifox.android.data.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class GetAnimePagingUseCase(private val remoteDataSource: RemoteDataSource){
    operator fun invoke(
        season: String? = null,
        ratingMpa: String? = null,
        minimalAge: String? = null,
        type: String? = null,
        order: String? = null,
        status: String? = null,
        genres: List<String>? = null,
        searchQuery: String? = null,
        year: Int? = null
    ): Flow<PagingData<AnimeLightEntity>> {
        return remoteDataSource.getAnimeLights(
            season = season,
            ratingMpa = ratingMpa,
            minimalAge = minimalAge,
            type = type,
            order = order,
            status = status,
            searchQuery = searchQuery,
            year = year,
            genres = genres
        )
    }
}