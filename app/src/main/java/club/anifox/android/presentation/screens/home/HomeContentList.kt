package club.anifox.android.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import club.anifox.android.R
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.components.horizontalContent.ScrollableHorizontalContent
import club.anifox.android.presentation.components.item.ItemVerticalModifier

private object HomeContentListSection {
    const val TradingAnime = "trading_anime"
}

@Composable
fun HomeContentList(
    lazyColumnState: LazyListState = rememberLazyListState(),
    trendingAnimeState: StateListWrapper<AnimeLight>,
) {
    LazyColumn(
        state = lazyColumnState,
    ) {
        item(key = HomeContentListSection.TradingAnime) {
            ScrollableHorizontalContent(
                headerTitle = stringResource(R.string.home_popular_title),
                contentState = trendingAnimeState,
                contentPadding = PaddingValues(horizontal = 12.dp),
                contentArrangement = ItemVerticalModifier.HorizontalArrangement.Default,
                onIconClick = { /*TODO*/ },
                onItemClick = { },
            )
        }
    }
}
