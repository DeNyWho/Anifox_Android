package club.anifox.android.presentation.screens.pickgenre

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.anifox.android.domain.model.anime.common.AnimeGenres
import club.anifox.android.domain.model.user.account.TokenPair
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.domain.usecase.anime.GetAnimeGenresUseCase
import club.anifox.android.domain.usecase.datastore.token.GetUserTokenUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PickGenreViewModel(
    private val getAnimeGenresUseCase: GetAnimeGenresUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
) : ViewModel() {

    private val _genres: MutableState<StateListWrapper<AnimeGenres>> =
        mutableStateOf(StateListWrapper())
    val genres: MutableState<StateListWrapper<AnimeGenres>> = _genres

    private val _tokens: MutableState<StateWrapper<TokenPair>> =
        mutableStateOf(StateWrapper())
    val tokens: MutableState<StateWrapper<TokenPair>> = _tokens

    init {
        fetchToken()
    }

    private fun fetchToken() {
        viewModelScope.launch {
            getUserTokenUseCase.invoke().collect {
                _tokens.value = it
            }
        }
    }

    fun getGenres() {
        getAnimeGenresUseCase.invoke().onEach {
            _genres.value = it
        }.launchIn(viewModelScope)
    }
}
