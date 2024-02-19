package club.anifox.android.presentation.screens.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.state.StateListWrapper


data class HomeUIParam(
    val popularAnimeState: StateListWrapper<AnimeLight>,
    val ongoingAnimeState: StateListWrapper<AnimeLight>,
)

private val DataSet = List(10) {
    AnimeLight(
        title = "Провожающая в последний путь Фрирен",
        image = "https://cdn.anifox.club/images/anime/large/provozhaiushchaia-v-poslednii-put-friren/08f43e5054966f85ed4bcdbe7dc77b7b.png",
        url = "provozhaiushchaia-v-poslednii-put-friren$it"
    )
}

class HomeUIProvider: PreviewParameterProvider<HomeUIParam> {
    override val count: Int
        get() = super.count
    override val values: Sequence<HomeUIParam>
        get() = listOf(
            HomeUIParam(
                popularAnimeState = StateListWrapper.loading(),
                ongoingAnimeState = StateListWrapper.loading(),
            ),
            HomeUIParam(
                popularAnimeState = StateListWrapper(data = DataSet, isLoading = false),
                ongoingAnimeState = StateListWrapper(data = DataSet, isLoading = false),
            )
        ).asSequence()
}


