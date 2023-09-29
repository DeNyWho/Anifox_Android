package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetAnimeScreenShotsUseCase(private val animeService: AnimeService) {

    operator fun invoke(url: String): Flow<StateListWrapper<String>> {
        return flow {
            emit(StateListWrapper.loading())

            val state = when(val result = animeService.getAnimeScreenshots(url)) {
                is Resource.Success -> {
                    val data = result.data
                    StateListWrapper(data)
                }
                is Resource.Error -> {
                    StateListWrapper(error = result.error)
                }
                is Resource.Loading -> {
                    StateListWrapper.loading()
                }
            }

            emit(state)
        }
    }
}