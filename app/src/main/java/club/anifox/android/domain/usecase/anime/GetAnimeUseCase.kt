package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.local.mappers.toAnimeLight
import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.enums.search.FilterEnum
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAnimeUseCase(private val service: AnimeService) {
    operator fun invoke(
        season: String? = null,
        ratingMpa: String? = null,
        minimalAge: String? = null,
        type: String? = null,
        order: String? = null,
        pageNum: Int = 0,
        pageSize: Int = 24,
        status: String? = null,
        genres: List<String>? = null,
        searchQuery: String? = null,
        year: Int? = null,
        filter: FilterEnum? = null,
    ): Flow<StateListWrapper<AnimeLight>> {
        return flow {
            emit(StateListWrapper.loading())

            val animeResult = service.getAnime(
                order = order,
                page = pageNum,
                limit = pageSize,
                status = status,
                genres = genres,
                searchQuery = searchQuery,
                minimalAge = minimalAge,
                ratingMpa = ratingMpa,
                season = season,
                type = type,
                year = year,
                filter = filter,
            )

            val state = when (animeResult) {
                is Resource.Success -> {
                    val data = animeResult.data.map { it.toAnimeLight() }
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
