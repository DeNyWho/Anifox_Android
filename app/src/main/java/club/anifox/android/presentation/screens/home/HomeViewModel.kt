package club.anifox.android.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.request.FilterEnum
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.usecase.anime.AnimeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val animeUseCase: AnimeUseCase,
): ViewModel() {
    private val _onPopularAnime: MutableState<StateListWrapper<AnimeLight>> =
        mutableStateOf(StateListWrapper())
    val onPopularAnime: MutableState<StateListWrapper<AnimeLight>> = _onPopularAnime
    private val _onOngoingAnime: MutableState<StateListWrapper<AnimeLight>> =
        mutableStateOf(StateListWrapper())
    val onOngoingAnime: MutableState<StateListWrapper<AnimeLight>> = _onOngoingAnime

    fun getPopular(page: Int, limit: Int) {
        animeUseCase.invoke(page = page, limit = limit, filter = FilterEnum.ShikimoriRating).onEach {
            _onPopularAnime.value = it
        }.launchIn(viewModelScope)
    }

    fun getOngoing(page: Int, limit: Int, currentSeason: AnimeSeason, year: Int) {
        animeUseCase.invoke(page = page, limit = limit, status = AnimeStatus.Ongoing, filter = FilterEnum.ShikimoriRating, season = currentSeason, year = year).onEach {
            _onOngoingAnime.value = it
        }.launchIn(viewModelScope)
    }

}
