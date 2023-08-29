package club.anifox.android.presentation.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ItemVerticalShimmer(
    modifier: Modifier,
    shimmerInstance: Shimmer,
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault
) {
    Column(
        modifier = modifier
            .shimmer(shimmerInstance)
    ) {
        Card (
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(thumbnailHeight)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )

        Box(
            modifier = Modifier
                .width(62.dp)
                .height(18.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}

fun LazyGridScope.showItemVerticalShimmer(
    modifier: Modifier = ItemVerticalModifier.fillParentWidth,
    shimmerInstance: Shimmer,
    count: Int = 11,
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault
) {
    items(count) {
        ItemVerticalShimmer(
            modifier = modifier,
            shimmerInstance = shimmerInstance,
            thumbnailHeight = thumbnailHeight
        )
    }
}

fun LazyListScope.showItemVerticalShimmer(
    modifier: Modifier = ItemVerticalModifier.fillParentWidth,
    shimmerInstance: Shimmer,
    count: Int = 11,
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault
) {
    items(count) {
        ItemVerticalShimmer(
            modifier = modifier,
            shimmerInstance = shimmerInstance,
            thumbnailHeight = thumbnailHeight
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        ItemVerticalShimmer(
            Modifier.width(ItemVerticalModifier.Default),
            rememberShimmerCustomBounds()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        ItemVerticalShimmer(
            Modifier.width(ItemVerticalModifier.Default),
            rememberShimmerCustomBounds()
        )
    }
}