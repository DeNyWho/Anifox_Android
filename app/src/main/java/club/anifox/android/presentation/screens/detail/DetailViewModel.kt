package club.anifox.android.presentation.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.anime.related.AnimeRelated
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.domain.usecase.anime.GetAnimeDetailsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeRelatedUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeScreenShots
import club.anifox.android.domain.usecase.anime.GetAnimeSimilarUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailViewModel(
    private val detailsUseCase: GetAnimeDetailsUseCase,
    private val screenShotsUseCase: GetAnimeScreenShots,
    private val relatedUseCase: GetAnimeRelatedUseCase,
    private val similarUseCase: GetAnimeSimilarUseCase
): ViewModel() {

    private val _detailAnime: MutableState<StateWrapper<AnimeDetail>> =
        mutableStateOf(StateWrapper())
    val detailAnime: MutableState<StateWrapper<AnimeDetail>> = _detailAnime

    private val _screenShots: MutableState<StateListWrapper<String>> =
        mutableStateOf(StateListWrapper())
    val screenShots: MutableState<StateListWrapper<String>> = _screenShots

    private val _related: MutableState<StateListWrapper<AnimeRelated>> =
        mutableStateOf(StateListWrapper())
    val related: MutableState<StateListWrapper<AnimeRelated>> = _related

    private val _similar: MutableState<StateListWrapper<AnimeLight>> =
        mutableStateOf(StateListWrapper())
    val similar: MutableState<StateListWrapper<AnimeLight>> = _similar

    fun getDetail(url: String) {
        detailsUseCase.invoke(url).onEach {
            _detailAnime.value = it
        }.launchIn(viewModelScope)
    }

    fun getSimilar(url: String) {
        similarUseCase.invoke(url).onEach {
            _similar.value = it
        }.launchIn(viewModelScope)
    }

    fun getRelated(url: String) {
        relatedUseCase.invoke(url).onEach {
            _related.value = it
        }.launchIn(viewModelScope)
    }

    fun getScreenShots(url: String) {
        screenShotsUseCase.invoke(url).onEach {
            _screenShots.value = it
        }.launchIn(viewModelScope)
    }
}