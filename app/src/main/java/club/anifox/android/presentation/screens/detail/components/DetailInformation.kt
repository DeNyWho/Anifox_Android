package club.anifox.android.presentation.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import club.anifox.android.domain.model.anime.AnimeStudio
import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.util.formatDateWithMonth

@Composable
fun DetailInformation(data: AnimeDetail) {
    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
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
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
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
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
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
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
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
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = data.studio.joinToString(separator = ", ") { it.studio },
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        DetailInformation(
            AnimeDetail(
                episodesCount = 12,
                episodesCountAired = 12,
                minimalAge = 18,
                status = "released",
                type = "tv",
                studio = listOf(
                    AnimeStudio(
                        id = "1",
                        studio = "Bones"
                    ),
                    AnimeStudio(
                        id = "2",
                        studio = "White Fox"
                    ),
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        DetailInformation(
            AnimeDetail(
                episodesCount = 12,
                episodesCountAired = 12,
                minimalAge = 18,
                status = "released",
                type = "tv",
                studio = listOf(
                    AnimeStudio(
                        id = "1",
                        studio = "Bones"
                    ),
                    AnimeStudio(
                        id = "2",
                        studio = "White Fox"
                    ),
                )
            )
        )
    }
}