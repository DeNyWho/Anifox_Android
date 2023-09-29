package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.anime.AnimeUsersStatus
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.toAnimeUsersStatus
import club.anifox.android.domain.state.StateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAnimeUsersStatusUseCase(private val service: AnimeService){
    operator fun invoke(
        url: String
    ): Flow<StateWrapper<AnimeUsersStatus>> {
        return flow {
            emit(StateWrapper.loading())

            val state = when (val animeResult = service.getAnimeUsersStatus(url)) {
                is Resource.Success -> {
                    val data = animeResult.data.toAnimeUsersStatus()
                    StateWrapper(data)
                }
                is Resource.Error -> {
                    StateWrapper(error = animeResult.error)
                }
                is Resource.Loading -> {
                    StateWrapper.loading()
                }
            }

            emit(state)

        }.flowOn(Dispatchers.IO)
    }
}