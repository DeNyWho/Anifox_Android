package club.anifox.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import club.anifox.android.domain.usecase.anime.AnimeUseCase

class HomeViewModel(
    private val animeUseCase: AnimeUseCase,
): ViewModel() {

}
