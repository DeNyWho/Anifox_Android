package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.remote.AnimeService
import club.anifox.android.domain.model.anime.ContentLight
import club.anifox.android.domain.model.anime.toContentLight
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAnimeUseCase(private val service: AnimeService){
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
        year: Int? = null
    ): Flow<StateListWrapper<ContentLight>> {
        return flow {
            emit(StateListWrapper.loading())

            val animeResult = service.getAnime(
                order = order,
                pageNum = pageNum,
                pageSize = pageSize,
                status = status,
                genres = genres,
                searchQuery = searchQuery,
                minimalAge = minimalAge,
                ratingMpa = ratingMpa,
                season = season,
                type = type,
                year = year
            )

            val state = when (animeResult) {
                is Resource.Success -> {
                    val data = animeResult.data.data?.map { it.toContentLight() }
                    println("TEST TEST = $data")
                    StateListWrapper(data)
                }
                is Resource.Error -> {
                    println("TEST TEST = ${animeResult.error}")
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