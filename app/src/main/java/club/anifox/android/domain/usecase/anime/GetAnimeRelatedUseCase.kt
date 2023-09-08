package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.remote.AnimeService
import club.anifox.android.domain.model.anime.related.AnimeRelated
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.related.toAnimeRelated
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAnimeRelatedUseCase(private val service: AnimeService){
    operator fun invoke(
        url: String
    ): Flow<StateListWrapper<AnimeRelated>> {
        return flow {
            emit(StateListWrapper.loading())

            val state = when (val animeResult = service.getAnimeRelated(url)) {
                is Resource.Success -> {
                    val data = animeResult.data.data?.map { it.toAnimeRelated() }
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