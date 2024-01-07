package club.anifox.android.presentation.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.common.AnimeUsersStatus
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.anime.related.AnimeRelated
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.domain.usecase.anime.GetAnimeDetailsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeRelatedUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeScreenShotsUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeSimilarUseCase
import club.anifox.android.domain.usecase.anime.GetAnimeUsersStatusUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailViewModel(
    private val detailsUseCase: GetAnimeDetailsUseCase,
    private val screenShotsUseCase: GetAnimeScreenShotsUseCase,
    private val relatedUseCase: GetAnimeRelatedUseCase,
    private val similarUseCase: GetAnimeSimilarUseCase,
    private val usersStatusUseCase: GetAnimeUsersStatusUseCase,
) : ViewModel() {

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

    private val _usersStatus: MutableState<StateWrapper<AnimeUsersStatus>> =
        mutableStateOf(StateWrapper())
    val usersStatus: MutableState<StateWrapper<AnimeUsersStatus>> = _usersStatus

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

    fun getUsersStatus(url: String) {
        usersStatusUseCase.invoke(url).onEach {
            _usersStatus.value = it
        }.launchIn(viewModelScope)
    }
}
