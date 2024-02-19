package club.anifox.android.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import club.anifox.android.presentation.components.horizontalContent.content.ScrollableHorizontalContent
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
) {
    LaunchedEffect(viewModel) {
        viewModel.getPopular(0,12)
        viewModel.getOngoing(0,12)
    }

    HomeUI(popularAnimeState = viewModel.onPopularAnime.value, ongoingAnimeState = viewModel.onOngoingAnime.value)
}

@Composable
private fun HomeUI(
    modifier: Modifier = Modifier,
    lazyColumnState: LazyListState = rememberLazyListState(),
    popularAnimeState: StateListWrapper<AnimeLight>,
    ongoingAnimeState: StateListWrapper<AnimeLight>,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        LazyColumn(
            state = lazyColumnState
        ) {
            item (HomeScreenSections.AnimePopular.name) {
                ScrollableHorizontalContent(headerTitle = HomeScreenSections.AnimePopular.headerTitle, contentState = popularAnimeState, onHeaderClick = {}, onItemClick = {})
            }
            item (HomeScreenSections.AnimeOngoing.name) {
                ScrollableHorizontalContent(headerTitle = HomeScreenSections.AnimeOngoing.headerTitle, contentState = ongoingAnimeState, onHeaderClick = {}, onItemClick = {})
            }

        }
    }

}


@PreviewLightDark
@Composable
private fun PreviewHomeUIDefault(
    @PreviewParameter(HomeUIProvider::class) param: HomeUIParam
) {
    AnifoxAndroidTheme {
        HomeUI(popularAnimeState = param.popularAnimeState, ongoingAnimeState = param.ongoingAnimeState)
    }
}
