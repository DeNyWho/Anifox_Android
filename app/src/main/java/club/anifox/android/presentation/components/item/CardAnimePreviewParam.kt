package club.anifox.android.presentation.components.item

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import club.anifox.android.domain.model.anime.AnimeLight

data class CardAnimePreviewParam(
    val modifier: Modifier = Modifier,
    val thumbnailHeight: Dp,
    val data: AnimeLight = AnimeLight(
        title = "Провожающая в последний путь Фрирен",
        image = "https://cdn.anifox.club/images/anime/large/provozhaiushchaia-v-poslednii-put-friren/08f43e5054966f85ed4bcdbe7dc77b7b.png",
        url = "provozhaiushchaia-v-poslednii-put-friren"
    ),
    val onClick: () -> Unit = {},
)

class CardAnimeProvider: PreviewParameterProvider<CardAnimePreviewParam> {
    override val values: Sequence<CardAnimePreviewParam>
        get() = listOf(
            CardAnimePreviewParam(
                modifier = Modifier.width(CardThumbnailPortraitDefault.Width.Default),
                thumbnailHeight = CardThumbnailPortraitDefault.Height.Default
            ),
            CardAnimePreviewParam(
                modifier = Modifier.width(CardThumbnailPortraitDefault.Width.Small),
                thumbnailHeight = CardThumbnailPortraitDefault.Height.Grid,
            ),
            CardAnimePreviewParam(
                modifier = Modifier.width(CardThumbnailPortraitDefault.Width.Small),
                thumbnailHeight = CardThumbnailPortraitDefault.Height.Small,
            )
        ).asSequence()
}
