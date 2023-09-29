package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.local.mappers.toAnimeLight
import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAnimeSimilarUseCase(private val service: AnimeService){
    operator fun invoke(
        url: String
    ): Flow<StateListWrapper<AnimeLight>> {
        return flow {
            emit(StateListWrapper.loading())

            val state = when (val animeResult = service.getAnimeSimilar(url)) {
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