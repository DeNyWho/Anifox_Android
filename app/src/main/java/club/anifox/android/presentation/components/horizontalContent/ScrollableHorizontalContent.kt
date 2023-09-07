package club.anifox.android.presentation.components.horizontalContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.components.item.ItemVertical
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import club.anifox.android.presentation.components.shimmer.ItemVerticalHeaderShimmer
import club.anifox.android.presentation.components.shimmer.onUpdateShimmerBounds
import club.anifox.android.presentation.components.shimmer.showItemVerticalShimmer
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun ScrollableHorizontalContent(
    modifier: Modifier = Modifier,
    headerModifier: Modifier = HorizontalContentHeaderConfig.Default,
    itemModifier: Modifier = Modifier.width(ItemVerticalModifier.Default),
    shimmer: Shimmer = rememberShimmer(ShimmerBounds.Custom),
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault,
    headerTitle: String,
    contentState: StateListWrapper<AnimeLight>,
    contentPadding: PaddingValues,
    contentArrangement: Arrangement.Horizontal,
    textAlign: TextAlign = TextAlign.Start,
    onIconClick: () -> Unit,
    onItemClick: (String) -> Unit,
    limit: Int = 11
) {
    if (contentState.isLoading) {
        ItemVerticalHeaderShimmer(modifier = headerModifier, shimmerInstance = shimmer)
    } else if (contentState.data != null) {
        HorizontalContentHeader(
            modifier = headerModifier,
            title = headerTitle,
            onButtonClick = if(contentState.data.size > limit) onIconClick else null
        )
    }

    LazyRow(
        modifier = modifier.onUpdateShimmerBounds(shimmer),
        contentPadding = contentPadding,
        horizontalArrangement = contentArrangement
    ) {
        if(contentState.isLoading) {
            showItemVerticalShimmer(
                modifier = itemModifier,
                shimmerInstance = shimmer,
                thumbnailHeight = thumbnailHeight,
                count = limit
            )
        } else if(contentState.data != null) {
            items(
                items = contentState.data, key =  {it.url}
            ) {data ->
                ItemVertical(
                    modifier = itemModifier,
                    data = data,
                    thumbnailHeight = thumbnailHeight,
                    textAlign = textAlign,
                    onClick = onItemClick
                )
            }
        }
    }

}