package club.anifox.android.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.presentation.components.fields.SearchBoxField
import club.anifox.android.presentation.components.grid.ScrollableGridContent
import club.anifox.android.presentation.components.shimmer.rememberShimmerCustomBounds
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = getViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.searchAnime("а")
    }

    Content(Modifier, viewModel.anime.collectAsLazyPagingItems(), viewModel::onSearchEvent) {
        viewModel.onSearchEvent(SearchEvent.ClearSearch)
        navController.popBackStack()
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    animeState: LazyPagingItems<AnimeLight>,
    onSearchEvent: (SearchEvent) -> Unit,
    navigateBack: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        SearchBoxField(
            modifier = Modifier.padding(bottom = 8.dp),
            isEnabled = true,
        )

        ScrollableGridContent(
            modifier = Modifier,
            lazyGridState = gridState,
            shimmer = rememberShimmerCustomBounds(),
            contentState = animeState,
            contentPadding = PaddingValues(top = 20.dp),
            onItemClick = {}
        )
    }
}

