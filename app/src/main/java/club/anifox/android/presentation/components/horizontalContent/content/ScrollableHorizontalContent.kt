package club.anifox.android.presentation.components.horizontalContent.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import club.anifox.android.presentation.components.horizontalContent.header.ContentListHeaderShimmer
import club.anifox.android.presentation.components.horizontalContent.header.HorizontalContentHeader
import club.anifox.android.presentation.components.item.CardThumbnailPortrait
import club.anifox.android.presentation.components.item.CardThumbnailPortraitDefault
import club.anifox.android.presentation.components.item.showCardThumbnailPortraitShimmer
import club.anifox.android.presentation.components.shimmer.onUpdateShimmerBounds
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun ScrollableHorizontalContent(
    modifier: Modifier = Modifier,
    headerModifier: Modifier = ScrollableHorizontalContentDefault.Default,
    itemModifier: Modifier = Modifier.width(CardThumbnailPortraitDefault.Width.Default),
    shimmer: Shimmer = rememberShimmer(ShimmerBounds.Custom),
    thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
    headerTitle: String,
    contentState: StateListWrapper<AnimeLight>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp),
    contentArrangement: Arrangement.Horizontal = CardThumbnailPortraitDefault.HorizontalArrangement.Default,
    textAlign: TextAlign = TextAlign.Start,
    onHeaderClick: () -> Unit,
    onItemClick: () -> Unit,
) {
    // header
    if(contentState.isLoading) {
        ContentListHeaderShimmer(
            modifier = headerModifier,
            shimmerInstance = shimmer,
        )
    } else if (contentState.data.isNotEmpty()) {
        HorizontalContentHeader(
            modifier = headerModifier,
            title = headerTitle,
            onButtonClick = onHeaderClick,
        )
    }

    // content
    LazyRow (
        modifier = modifier.onUpdateShimmerBounds(shimmer),
        contentPadding = contentPadding,
        horizontalArrangement = contentArrangement
    ) {
        if(contentState.isLoading) {
            showCardThumbnailPortraitShimmer(
                modifier = itemModifier,
                shimmerInstance = shimmer,
                thumbnailHeight = thumbnailHeight
            )
        } else if(contentState.data.isNotEmpty()) {
            items(
                contentState.data,
                key = { it.url }
            ) { data ->
                CardThumbnailPortrait(
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

@PreviewLightDark
@Composable
private fun PreviewScrollableHorizontalContentDefault(
    @PreviewParameter(ScrollableHorizontalContentProvider::class) param: ScrollableHorizontalContentParam
) {
    AnifoxAndroidTheme {
        Column (
            Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            ScrollableHorizontalContent(
                modifier = param.modifier,
                headerModifier = param.headerModifier,
                itemModifier = param.itemModifier,
                thumbnailHeight = param.thumbnailHeight,
                headerTitle = param.headerTitle,
                contentState = param.contentState,
                contentPadding = param.contentPadding,
                contentArrangement = param.contentArrangement,
                textAlign = param.textAlign,
                onHeaderClick = param.onHeaderClick,
                onItemClick = param.onItemClick
            )
        }
    }
}
