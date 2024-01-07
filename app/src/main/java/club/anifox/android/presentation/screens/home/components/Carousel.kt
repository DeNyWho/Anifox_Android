package club.anifox.android.presentation.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.components.item.ItemVertical
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    contentState: StateListWrapper<AnimeLight>,
) {
    val data = contentState.data
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { data?.size ?: 10 },
    )

    LaunchedEffect(Unit) {
        while (true) {
            val before = pagerState.currentPage
            delay(5_000) // 如果手动改变了 Pager，则不进行自动切换
            if (pagerState.currentPage == before) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
    if (contentState.isLoading) {
    } else if (data != null) {
        Box {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
                state = pagerState,
                pageSpacing = 0.dp,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                key = null,
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    Orientation.Horizontal,
                ),
                pageContent = {
                    val index = it % contentState.data.size
                    Box(
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            role = Role.Button,
                            onClick = { },
                        ),
                    ) {
                        ItemVertical(
                            modifier = Modifier.width(ItemVerticalModifier.Carousel),
                            data = data[index],
                            thumbnailHeight = ItemVerticalModifier.ThumbnailHeightCarousel,
                            textAlign = TextAlign.Start,
                            onClick = { },
                            needTitle = false,
                        )
                    }
                },
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 15.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                repeat(data.size) {
                    Box(
                        Modifier
                            .size(8.dp)
                            .padding(if (pagerState.currentPage % data.size == it) 0.dp else 1.dp)
                            .background(
                                color = if (pagerState.currentPage % data.size == it) {
                                    Color.White.copy(0.9f)
                                } else {
                                    Color.White.copy(0.4f)
                                },
                                shape = CircleShape,
                            ),
                    )
                }
            }
        }
    }
}
