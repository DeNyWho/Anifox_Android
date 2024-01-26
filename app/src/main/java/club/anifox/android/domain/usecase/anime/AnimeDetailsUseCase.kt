package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.local.dao.anime.AnimeDao
import club.anifox.android.data.mapper.toAnimeDetails
import club.anifox.android.data.network.service.AnimeService
import club.anifox.android.domain.model.anime.AnimeDetails
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.model.dto.anime.toDetails
import club.anifox.android.domain.model.dto.anime.toLight
import club.anifox.android.domain.state.StateWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime

class AnimeDetailsUseCase (
    private val animeDao: AnimeDao,
    private val service: AnimeService
) {
    operator fun invoke(
        url: String,
        fetchFromRemote: Boolean,
    ): Flow<StateWrapper<AnimeDetails>> {
        return flow {
            emit(StateWrapper.loading())

            val localAnime = animeDao.getAnimeDetails(url)
            if (localAnime != null) {
                emit(StateWrapper(localAnime.toAnimeDetails(), isLoading = true))
            }

            if((!fetchFromRemote && localAnime != null) || (localAnime != null && localAnime.anime.status == AnimeStatus.Released)) {
                emit(StateWrapper(isLoading = false))
                return@flow
            }

            val state = when (val animeResult = service.getAnimeDetails(url)) {
                is Resource.Success -> {
                    val data = animeResult.data.toDetails()

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

        }

    }
}
