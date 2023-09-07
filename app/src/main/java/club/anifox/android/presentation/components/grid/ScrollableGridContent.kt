package club.anifox.android.presentation.components.grid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.presentation.components.item.ItemVertical
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import club.anifox.android.presentation.components.shimmer.onUpdateShimmerBounds
import club.anifox.android.presentation.components.shimmer.showItemVerticalShimmer
import club.anifox.android.presentation.util.calculateGridCount
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun ScrollableGridContent(
    lazyGridState: LazyGridState,
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier.width(ItemVerticalModifier.Default),
    shimmer: Shimmer = rememberShimmer(ShimmerBounds.Custom),
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault,
    contentState: LazyPagingItems<AnimeLight>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    textAlign: TextAlign = TextAlign.Start,
    onItemClick: (String) -> Unit,
    limit: Int = 8,
    userScrollEnabled: Boolean = true,
    gridCells: GridCells = GridCells.Fixed(calculateGridCount(columnWidth = ItemVerticalModifier.Default)),
) {
    LazyVerticalGrid(
        modifier = modifier
            .onUpdateShimmerBounds(shimmer)
            .fillMaxSize()
            .heightIn(max = ItemVerticalModifier.ThumbnailHeightGrid * limit),
        columns = gridCells,
        state = lazyGridState,
        contentPadding = contentPadding,
        userScrollEnabled = userScrollEnabled,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement
    ) {
        items(contentState.itemCount) { index ->
            contentState[index]?.let {
                ItemVertical(
                    modifier = itemModifier,
                    data = it,
                    thumbnailHeight = thumbnailHeight,
                    textAlign = textAlign,
                    onClick = onItemClick
                )
            }
        }

        if (contentState.loadState.append == LoadState.Loading) {
            showItemVerticalShimmer(
                modifier = itemModifier,
                shimmerInstance = shimmer,
                thumbnailHeight = thumbnailHeight,
                count = limit
            )
        }
    }

}