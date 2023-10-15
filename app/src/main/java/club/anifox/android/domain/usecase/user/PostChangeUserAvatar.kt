package club.anifox.android.domain.usecase.user

import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.anime.AnimeGenres
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.toAnimeGenres
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostChangeUserAvatar(private val service: AnimeService) {
    operator fun invoke(): Flow<StateListWrapper<AnimeGenres>> {
        return flow {
            emit(StateListWrapper.loading())

            val state = when (val animeResult = service.getAnimeGenres()) {
                is Resource.Success -> {
                    val data = animeResult.data.map { it.toAnimeGenres() }
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
