package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.local.dao.anime.AnimeDao
import club.anifox.android.data.mapper.toAnimeLight
import club.anifox.android.data.network.service.AnimeService
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.AnimeType
import club.anifox.android.domain.model.anime.request.FilterEnum
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.toLight
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//class AnimeUseCase (
//    private val animeDao: AnimeDao,
//    private val service: AnimeService
//) {
//    operator fun invoke(
//        page: Int,
//        limit: Int,
//        status: AnimeStatus?,
//        genres: List<String>?,
//        searchQuery: String?,
//        season: AnimeSeason?,
//        ratingMpa: String?,
//        minimalAge: String?,
//        type: AnimeType?,
//        year: Int?,
//        studio: String?,
//        filter: FilterEnum?,
//        fetchFromRemote: Boolean,
//    ): Flow<StateListWrapper<AnimeLight>> {
//        return flow {
//            emit(StateListWrapper.loading())
//
//            val localAnime = animeDao.getAnime(status, season, type)
//            emit(StateListWrapper(localAnime.map { it.toAnimeLight() }, isLoading = true))
//
//            if(!fetchFromRemote && localAnime.isNotEmpty()) {
//                emit(StateListWrapper(isLoading = false))
//                return@flow
//            }
//
//            val animeResult = service.getAnime(
//                page = page,
//                limit = limit,
//                status = status,
//                genres = genres,
//                searchQuery = searchQuery,
//                season = season,
//                ratingMpa = ratingMpa,
//                minimalAge = minimalAge,
//                type = type,
//                year = year,
//                studio = studio,
//                filter = filter
//            )
//
//            val state = when (animeResult) {
//                is Resource.Success -> {
//                    val data = animeResult.data.map { it.toLight() }
//
//                    StateListWrapper(data)
//                }
//                is Resource.Error -> {
//                    StateListWrapper(error = animeResult.error)
//                }
//                is Resource.Loading -> {
//                    StateListWrapper.loading()
//                }
//            }
//
//            emit(state)
//
//        }
//
//    }
//}

class AnimeUseCase (private val service: AnimeService) {
    operator fun invoke(
        page: Int = 0,
        limit: Int = 12,
        status: AnimeStatus? = null,
        genres: List<String>? = null,
        searchQuery: String? = null,
        season: AnimeSeason? = null,
        ratingMpa: String? = null,
        minimalAge: String? = null,
        type: AnimeType? = null,
        year: Int? = null,
        studio: String? = null,
        filter: FilterEnum? = null,
    ): Flow<StateListWrapper<AnimeLight>> {
        return flow {
            emit(StateListWrapper.loading())

            val animeResult = service.getAnime(
                page = page,
                limit = limit,
                status = status,
                genres = genres,
                searchQuery = searchQuery,
                season = season,
                ratingMpa = ratingMpa,
                minimalAge = minimalAge,
                type = type,
                year = year,
                studio = studio,
                filter = filter
            )

            val state = when (animeResult) {
                is Resource.Success -> {
                    val data = animeResult.data.map { it.toLight() }
                    StateListWrapper(data)
                }
                is Resource.Error -> {
                    StateListWrapper(error = animeResult.error)
                }
                is Resource.Loading -> {
                    StateListWrapper.loading()
                }
            }

            emit(state)
        }.flowOn(Dispatchers.IO)
    }
}

