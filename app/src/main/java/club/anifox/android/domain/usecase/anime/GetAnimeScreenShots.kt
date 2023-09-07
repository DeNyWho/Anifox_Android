package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.remote.AnimeService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetAnimeScreenShots(private val animeService: AnimeService) {

    operator fun invoke(url: String): Flow<StateWrapper<String>> {
        return flow {
            emit(StateWrapper.loading())

            val state = when(val result = animeService.getAnimeScreenshots(url)) {
                is Resource.Success -> {
                    val data = result.data
                    StateWrapper(data)
                }
                is Resource.Error -> {
                    StateWrapper(error = result.error)
                }
                is Resource.Loading -> {
                    StateWrapper.loading()
                }
            }

            emit(state)
        }
    }
}