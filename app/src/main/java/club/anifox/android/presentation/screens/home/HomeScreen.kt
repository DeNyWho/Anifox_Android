package club.anifox.android.presentation.screens.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = getViewModel(),
    lazyColumnState: LazyListState = rememberLazyListState(),
) {
    Content(modifier, lazyColumnState)
}

@Composable
private fun Content(
    modifier: Modifier,
    lazyColumnState: LazyListState,
    trendingAnimeState: StateListWrapper<AnimeLight>,
) {
    HomeContentList()
}

// @Preview(showBackground = true)
// @Composable
// private fun LightPreview() {
//    Anifox_AndroidTheme(useDarkTheme = false) {
//        Content(Modifier)
//    }
// }
//
// @Preview(showBackground = true)
// @Composable
// private fun DarkPreview() {
//    Anifox_AndroidTheme(useDarkTheme = true) {
//        Content(Modifier)
//    }
// }
