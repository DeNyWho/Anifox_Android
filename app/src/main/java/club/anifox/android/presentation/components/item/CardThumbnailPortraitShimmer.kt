package club.anifox.android.presentation.components.item

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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import club.anifox.android.presentation.components.shimmer.rememberShimmerCustomBounds
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun CardThumbnailPortraitShimmer(
    modifier: Modifier = Modifier,
    shimmerInstance: Shimmer,
    thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
) {
    Column(
        modifier = modifier
            .padding(bottom = 8.dp)
            .shimmer(shimmerInstance),
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp,
            ),
            shape = MaterialTheme.shapes.small,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(thumbnailHeight)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
        )

        Box(
            modifier = Modifier
                .width(62.dp)
                .height(24.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}

fun LazyGridScope.showCardThumbnailPortraitShimmer(
    shimmerInstance: Shimmer,
    count: Int = 11,
    thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
) {
    items(count) {
        CardThumbnailPortraitShimmer(
            shimmerInstance = shimmerInstance,
            thumbnailHeight = thumbnailHeight,
        )
    }
}

fun LazyListScope.showCardThumbnailPortraitShimmer(
    modifier: Modifier,
    shimmerInstance: Shimmer,
    count: Int = 11,
    thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
) {
    items(count) {
        CardThumbnailPortraitShimmer(
            modifier = modifier,
            shimmerInstance = shimmerInstance,
            thumbnailHeight = thumbnailHeight,
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewCardThumbnailPortraitShimmer() {
    AnifoxAndroidTheme {
        CardThumbnailPortraitShimmer(
            Modifier.width(CardThumbnailPortraitDefault.Width.Default),
            rememberShimmerCustomBounds(),
        )
    }
}
