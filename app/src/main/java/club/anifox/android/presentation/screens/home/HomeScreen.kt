package club.anifox.android.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.navigation.Screens
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.components.horizontalContent.HorizontalContentHeaderConfig
import club.anifox.android.presentation.components.horizontalContent.ScrollableHorizontalContent
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import club.anifox.android.presentation.components.shimmer.rememberShimmerCustomBounds
import club.anifox.android.presentation.screens.home.components.Carousel
import org.koin.androidx.compose.getViewModel

private object ContentHomeScreenSections {
    const val contentOngoing = "content_ongoing"
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = getViewModel(),
) {
    LaunchedEffect(viewModel) {
        viewModel.getOngoing(12, 0)
    }

    Content(
        modifier = modifier,
        onGoingAnimeState = viewModel.onGoingAnime.value,
        onSearchClick = {
            navController.navigate(Screens.Search.route)
        },
        onItemClick = { url ->
            navController.navigate("${Screens.Detail.route}/$url")
        },
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    lazyColumnState: LazyListState = rememberLazyListState(),
    onGoingAnimeState: StateListWrapper<AnimeLight>,
    onSearchClick: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = lazyColumnState,
            horizontalAlignment = Alignment.Start,
        ) {
            item { Carousel(contentState = onGoingAnimeState) }
            item(ContentHomeScreenSections.contentOngoing) {
                ScrollableHorizontalContent(
                    modifier = Modifier,
                    headerModifier = HorizontalContentHeaderConfig.NullableStart,
                    shimmer = rememberShimmerCustomBounds(),
                    headerTitle = stringResource(R.string.status_ongoing),
                    contentState = onGoingAnimeState,
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    contentArrangement = ItemVerticalModifier.HorizontalArrangement.Default,
                    onIconClick = {
                    },
                    onItemClick = { url ->
                        onItemClick.invoke(url)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        Content(
            modifier = Modifier,
            onGoingAnimeState = StateListWrapper(),
            onSearchClick = {},
            onItemClick = { url ->
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        Content(
            modifier = Modifier,
            onGoingAnimeState = StateListWrapper(),
            onSearchClick = {},
            onItemClick = { url ->
            },
        )
    }
}
