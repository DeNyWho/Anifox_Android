package club.anifox.android.presentation.components.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.unclippedBoundsInWindow

@Composable
fun rememberShimmerCustomBounds(): Shimmer {
    return rememberShimmer(shimmerBounds = ShimmerBounds.Custom)
}

@Stable
fun Modifier.onUpdateShimmerBounds(
    shimmerInstance: Shimmer,
) = this.then(
    onGloballyPositioned { value: LayoutCoordinates ->
        val position = value.unclippedBoundsInWindow()
        shimmerInstance.updateBounds(position)
    },
)
