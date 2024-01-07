package club.anifox.android.presentation.screens.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import club.anifox.android.R
import club.anifox.android.domain.enums.BrowseStateGrid
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.navigation.Screens
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.components.fields.SearchBoxField
import club.anifox.android.presentation.components.horizontalContent.HorizontalContentHeaderConfig
import club.anifox.android.presentation.components.horizontalContent.ScrollableHorizontalContent
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import club.anifox.android.presentation.components.shimmer.rememberShimmerCustomBounds
import org.koin.androidx.compose.getViewModel

private object ContentScreenSections {
    const val ContentOngoing = "content_ongoing"
}

@Composable
fun BrowseScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: BrowseViewModel = getViewModel(),
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
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
        ) {
            SearchBoxField(
                modifier = Modifier
                    .clickable(
                        onClick = { onSearchClick.invoke() },
                    ),
                isEnabled = false,
            )
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 16.dp),
            columns = GridCells.Fixed(2),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(BrowseStateGrid.entries.toTypedArray()) { state ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            state.onClick
                        }
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = MaterialTheme.shapes.small,
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = state.icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically),
                            color = MaterialTheme.colorScheme.onBackground,
                            text = state.label,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = lazyColumnState,
            horizontalAlignment = Alignment.Start,
        ) {
            item(ContentScreenSections.ContentOngoing) {
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
