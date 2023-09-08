package club.anifox.android.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import club.anifox.android.R
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.anime.related.AnimeRelated
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.presentation.components.horizontalContent.HorizontalContentHeaderConfig
import club.anifox.android.presentation.components.horizontalContent.ScrollableHorizontalContent
import club.anifox.android.presentation.components.item.ItemVerticalModifier
import club.anifox.android.presentation.components.shimmer.rememberShimmerCustomBounds
import club.anifox.android.presentation.screens.detail.components.ContentDetailsScreenToolbar
import club.anifox.android.presentation.screens.detail.components.DetailDescription
import club.anifox.android.presentation.screens.detail.components.DetailInformation
import kotlinx.coroutines.async
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.getViewModel

private object ContentDetailsScreenSection {
    const val ContentData = "content_data"
    const val ContentDescription = "content_description"
    const val ContentGenre = "content_genre_chips"
    const val ContentScreenshots = "content_screenshots"
    const val ContentMedia = "content_media"
    const val ContentSimilar = "content_similar"
    const val ContentPhotos = "content_photos"
    const val ContentRelated = "content_related"
}

@Composable
fun DetailScreen(
    url: String,
    navigateBack: () -> Boolean,
    onItemClick: (String) -> Unit,
    viewModel: DetailViewModel = getViewModel ()
) {
    LaunchedEffect(Unit) {
        val detailDeferred = async { viewModel.getDetail(url) }
        val screenshotsDeferred = async { viewModel.getScreenShots(url) }
        val relatedDeferred = async { viewModel.getRelated(url) }
        val similarDeferred = async { viewModel.getSimilar(url) }

        val detailResult = detailDeferred.await()
        val screenshotsResult = screenshotsDeferred.await()
        val relatedResult = relatedDeferred.await()
        val similarResult = similarDeferred.await()

        // Здесь вы можете обработать результаты запросов
        // Например, передать их во ViewModel или выполнить другие действия.
    }

    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        state = toolbarScaffoldState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            ContentDetailsScreenToolbar(
                contentDetailState = viewModel.detailAnime.value,
                toolbarScaffoldState = toolbarScaffoldState,
                navigateBack = navigateBack
            )
        }
    ) {
        Content(
            detailState = viewModel.detailAnime.value,
            screenshotsState = viewModel.screenShots.value,
            similarState = viewModel.similar.value,
            relatedState = viewModel.related.value,
            onItemClick = onItemClick,
        )
    }
}

@Composable
private fun Content(
    detailState: StateWrapper<AnimeDetail>,
    screenshotsState: StateListWrapper<String>,
    similarState: StateListWrapper<AnimeLight>,
    relatedState: StateListWrapper<AnimeRelated>,
    onItemClick: (String) -> Unit
) {
    if(detailState.isLoading || detailState.data == null) {

    } else {
        val data = detailState.data
        var isDescriptionExpanded by remember { mutableStateOf(false) }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = data.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Button(
                    onClick = {},
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(50.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 4.dp,
                        disabledElevation = 0.dp,
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp),
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(R.string.watch_button_title),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            item(key = ContentDetailsScreenSection.ContentData) {
                DetailInformation(data = data)
            }
            item(key = ContentDetailsScreenSection.ContentDescription) {
                DetailDescription(
                    data = data,
                    isExpanded = isDescriptionExpanded,
                    onExpandedChanged = { isDescriptionExpanded = it }
                )
            }
            item(key = ContentDetailsScreenSection.ContentRelated) {
                ScrollableHorizontalContent(
                    modifier = Modifier,
                    shimmer = rememberShimmerCustomBounds(),
                    headerTitle = stringResource(R.string.related_section),
                    contentState = StateListWrapper(
                        relatedState.data?.map { related ->
                            AnimeLight(
                                url = related.anime.url,
                                title = related.anime.title,
                                image = related.anime.image
                            )
                        },
                        isLoading = relatedState.isLoading,
                        error = relatedState.error
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    contentArrangement = ItemVerticalModifier.HorizontalArrangement.Default,
                    headerModifier = HorizontalContentHeaderConfig.NullableStart,
                    onIconClick = {
                    },
                    onItemClick = { url ->
                        onItemClick.invoke(url)
                    },
                )
            }
            item(key = ContentDetailsScreenSection.ContentSimilar) {
                ScrollableHorizontalContent(
                    modifier = Modifier,
                    headerModifier = HorizontalContentHeaderConfig.NullableStart,
                    shimmer = rememberShimmerCustomBounds(),
                    headerTitle = stringResource(R.string.similar_section),
                    contentState = similarState,
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    contentArrangement = ItemVerticalModifier.HorizontalArrangement.Default,
                    onIconClick = {
                    },
                    onItemClick = { url ->
                        onItemClick.invoke(url)
                    },
                )
            }
        }
    }
}