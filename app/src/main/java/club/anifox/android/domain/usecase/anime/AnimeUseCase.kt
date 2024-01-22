package club.anifox.android.domain.usecase.anime

import club.anifox.android.data.local.dao.anime.AnimeDao
import club.anifox.android.data.network.service.AnimeService
import club.anifox.android.domain.model.user.UserSession
import kotlinx.coroutines.flow.Flow

class AnimeUseCase (
    private val animeDao: AnimeDao,
    private val service: AnimeService
) {
    operator fun invoke() {

    }
}
