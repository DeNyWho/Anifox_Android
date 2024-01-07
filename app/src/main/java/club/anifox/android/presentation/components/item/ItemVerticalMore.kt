package club.anifox.android.presentation.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ItemVerticalMore(
    modifier: Modifier = Modifier,
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(thumbnailHeight)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.surface)
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(32.dp)
                    .padding(bottom = 6.dp),
            )
            Text(
                text = "Показать ещё",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

fun LazyListScope.showItemVerticalMoreWhenPastLimit(
    modifier: Modifier = Modifier.width(ItemVerticalModifier.Default),
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault,
    limit: Int = 12,
    size: Int = 0,
    onClick: () -> Unit,
) {
    if (size > limit) {
        item {
            ItemVerticalMore(
                modifier = modifier,
                thumbnailHeight = thumbnailHeight,
                onClick = onClick,
            )
        }
    }
}

@Preview
@Composable
fun Preview_ItemVerticalAnimeMore() {
    ItemVerticalMore(
        modifier = Modifier.width(ItemVerticalModifier.Default),
        thumbnailHeight = ItemVerticalModifier.ThumbnailHeightDefault,
        onClick = { },
    )
}
