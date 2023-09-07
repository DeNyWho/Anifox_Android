package club.anifox.android.presentation.screens.browse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.usecase.anime.GetAnimeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BrowseViewModel(
    private val animeUseCase: GetAnimeUseCase
): ViewModel() {
    private val _onGoingAnime: MutableState<StateListWrapper<AnimeLight>> =
        mutableStateOf(StateListWrapper())
    val onGoingAnime: MutableState<StateListWrapper<AnimeLight>> = _onGoingAnime

    fun getOngoing(pageSize: Int, pageNum: Int) {
        animeUseCase.invoke(pageNum = pageNum, pageSize = pageSize, status = "ongoing").onEach {
            _onGoingAnime.value = it
        }.launchIn(viewModelScope)
    }


}