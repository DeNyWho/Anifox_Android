package club.anifox.android.presentation.components.horizontalContent.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.state.StateListWrapper
import club.anifox.android.presentation.components.horizontalContent.header.HorizontalContentHeaderDefaults
import club.anifox.android.presentation.components.item.CardAnimePreviewParam
import club.anifox.android.presentation.components.item.CardThumbnailPortraitDefault

data class ScrollableHorizontalContentParam(
    val headerTitle: String = "Title",
    val contentState: StateListWrapper<AnimeLight>,
    val contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp),
    val contentArrangement: Arrangement.Horizontal,
    val modifier: Modifier = Modifier,
    val headerModifier: Modifier = HorizontalContentHeaderDefaults.Default,
    val itemModifier: Modifier = Modifier.width(CardThumbnailPortraitDefault.Width.Default),
    val thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
    val textAlign: TextAlign = TextAlign.Start,
    val onIconClick: () -> Unit = { },
    val onItemClick: () -> Unit = { },
)

private val DataSet = List(10) {
    AnimeLight(
        title = "Провожающая в последний путь Фрирен",
        image = "https://cdn.anifox.club/images/anime/large/provozhaiushchaia-v-poslednii-put-friren/08f43e5054966f85ed4bcdbe7dc77b7b.png",
        url = "provozhaiushchaia-v-poslednii-put-friren$it"
    )
}

class ScrollableHorizontalContentProvider: PreviewParameterProvider<ScrollableHorizontalContentParam> {
    override val count: Int
        get() = super.count
    override val values: Sequence<ScrollableHorizontalContentParam>
        get() = listOf(
            ScrollableHorizontalContentParam(
                modifier = Modifier,
                headerTitle = "Scrollable Default",
                contentArrangement = CardThumbnailPortraitDefault.HorizontalArrangement.Default,
                contentState = StateListWrapper.loading()
            ),
            ScrollableHorizontalContentParam(
                modifier = Modifier,
                headerTitle = "",
                contentArrangement = CardThumbnailPortraitDefault.HorizontalArrangement.Default,
                contentState = StateListWrapper(data = DataSet)
            ),
        ).asSequence()
}


