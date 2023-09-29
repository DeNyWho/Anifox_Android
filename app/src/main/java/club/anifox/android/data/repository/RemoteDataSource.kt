package club.anifox.android.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity
import club.anifox.android.data.local.database.AniFoxDataBase
import club.anifox.android.data.paging.AnimeLightNetworkMediator
import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val aniFoxDataBase: AniFoxDataBase,
    private val animeService: AnimeService
) {

    fun clearSearch(): Flow<Resource<Boolean>> {
        return flow {
            aniFoxDataBase.animeLightDao().clearAll()
            emit(Resource.Success(true))
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getAnimeLights(
        season: String?,
        minimalAge: String?,
        ratingMpa: String?,
        type: String?,
        status: String?,
        order: String?,
        genres: List<String>?,
        searchQuery: String?,
        year: Int?
    ): Flow<PagingData<AnimeLightEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = AnimeLightNetworkMediator(
                anifoxLocal = aniFoxDataBase,
                animeService = animeService,
                season = season,
                minimalAge = minimalAge,
                ratingMpa = ratingMpa,
                type = type,
                status = status,
                order = order,
                genres = genres,
                searchQuery = searchQuery,
                year = year
            ),
            pagingSourceFactory = { aniFoxDataBase.animeLightDao().getAnimeLights() }
        ).flow
    }
}