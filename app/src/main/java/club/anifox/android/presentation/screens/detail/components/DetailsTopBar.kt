package club.anifox.android.presentation.screens.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.presentation.common.ui.theme.darkGrey
import club.anifox.android.presentation.components.indicator.CenterCircularProgressIndicator
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import kotlin.math.roundToInt
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun CollapsingToolbarScope.ContentDetailsScreenToolbar(
    contentDetailState: StateWrapper<AnimeDetail>,
    toolbarScaffoldState: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState(),
    navigateBack: () -> Boolean,
) {
    val isTitleVisible = toolbarScaffoldState.toolbarState.progress <= 0.2
    println("ZZ = ${toolbarScaffoldState.toolbarState.progress}")
    val blockerColorGradients = listOf(
        darkGrey.copy(alpha = 0.8F),
        darkGrey.copy(alpha = 0.9F),
        darkGrey,
    )

    if (!contentDetailState.isLoading && contentDetailState.data != null) {
        val data = contentDetailState.data
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .parallax(0.5f)
                .graphicsLayer {
                    alpha = toolbarScaffoldState.toolbarState.progress
                },
        ) {
            Box {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(contentDetailState.data.largeImage)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Item poster image",
                    loading = {
                        CenterCircularProgressIndicator(
                            strokeWidth = 2.dp,
                            size = 20.dp,
                        )
                    },
                    imageLoader = ImageLoader.Builder(LocalContext.current).build(),
                    onError = {
                        println(it.result.throwable.message)
                    },
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(colors = blockerColorGradients),
                        ),
                )
            }
            Card(
                modifier = Modifier
                    .align(Alignment.Center),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 2.dp,
                ),
                shape = MaterialTheme.shapes.small,
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .width(200.dp)
                        .height(300.dp)
                        .clip(MaterialTheme.shapes.small),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(contentDetailState.data.largeImage)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = "Item vertical image",
                    contentScale = ContentScale.Crop,
                    loading = {
                        CenterCircularProgressIndicator(
                            strokeWidth = 2.dp,
                            size = 20.dp,
                        )
                    },
                    imageLoader = ImageLoader.Builder(LocalContext.current).build(),
                    onError = {
                        println(it.result.throwable.message)
                    },
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navigateBack.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }

            val density = LocalDensity.current
            val initialOffset = with(density) {
                40.dp.toPx().roundToInt()
            }
            val targetOffset = with(density) {
                -40.dp.toPx().roundToInt()
            }

            AnimatedVisibility(
                visible = isTitleVisible,
                enter = slideInVertically(
                    initialOffsetY = { initialOffset },
                    animationSpec = tween(
                        durationMillis = 800,
                        delayMillis = 50,
                        easing = FastOutSlowInEasing,
                    ),
                ),
                exit = slideOutVertically(
                    targetOffsetY = { targetOffset },
                    animationSpec = tween(
                        durationMillis = 800,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing,
                    ),
                ) + fadeOut(),
            ) {
                Text(
                    text = data.title ?: "-",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 12.dp),
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .parallax(0.5f)
                .graphicsLayer {
                    alpha = toolbarScaffoldState.toolbarState.progress
                },
        ) {
        }
    }
}
