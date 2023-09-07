package club.anifox.android.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import club.anifox.android.R
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.state.StateWrapper
import club.anifox.android.presentation.screens.detail.components.ContentDetailsScreenToolbar
import club.anifox.android.presentation.util.formatDateWithMonth
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    url: String,
    navigateBack: () -> Boolean,
    viewModel: DetailViewModel = getViewModel ()
) {
    LaunchedEffect(viewModel) {
        viewModel.getDetail(url)
        viewModel.getScreenShots(url)
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
        Content(viewModel.detailAnime.value)
    }
}

@Composable
private fun Content(stateWrapper: StateWrapper<AnimeDetail>) {
    if(stateWrapper.isLoading || stateWrapper.data == null) {

    } else {
        val data = stateWrapper.data
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxSize()
        ) {
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
                    .padding(top = 16.dp)
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

            Column (
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = when(data.type) {
                            "tv" -> "ТВ сериал"
                            else -> data.type },
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${data.episodesCountAired}/${data.episodesCount}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Block,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${data.minimalAge}+",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = buildString {
                            append("с ${formatDateWithMonth(data.airedAt)}")
                            if (data.status != "ongoing") {
                                append(" по ${formatDateWithMonth(data.releasedAt)}")
                            }
                        },
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = data.studio.joinToString(separator = ", ") { it.studio },
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Text(
                text = stringResource(R.string.description_title),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}