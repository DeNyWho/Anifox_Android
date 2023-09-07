package club.anifox.android.presentation.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.domain.usecase.anime.GetAnimeDetailsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeScreenShots
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailViewModel(
    private val detailsUseCase: GetAnimeDetailsUseCase,
    private val screenShotsUseCase: GetAnimeScreenShots
): ViewModel() {

    private val _detailAnime: MutableState<StateWrapper<AnimeDetail>> =
        mutableStateOf(StateWrapper())
    val detailAnime: MutableState<StateWrapper<AnimeDetail>> = _detailAnime

    private val _screenShots: MutableState<StateWrapper<String>> =
        mutableStateOf(StateWrapper())
    val screenShots: MutableState<StateWrapper<String>> = _screenShots

    fun getDetail(url: String) {
        detailsUseCase.invoke(url).onEach {
            _detailAnime.value = it
        }.launchIn(viewModelScope)
    }

    fun getScreenShots(url: String) {
        screenShotsUseCase.invoke(url).onEach {
            _screenShots.value = it
        }.launchIn(viewModelScope)
    }
}