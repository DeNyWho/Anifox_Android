package club.anifox.android.presentation.components.horizontalContent.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ContentListHeaderShimmer(
    modifier: Modifier = Modifier,
    shimmerInstance: Shimmer,
    showButton: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp)
            .shimmer(shimmerInstance),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = modifier
                .width(110.dp)
                .height(32.dp)
                .padding(0.dp, 6.dp, 0.dp, 0.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
        )

        if (showButton) {
            Box(
                modifier = modifier
                    .size(32.dp)
                    .padding(0.dp, 6.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = MaterialTheme.colorScheme.onSurfaceVariant),
            )
        }
    }
}
