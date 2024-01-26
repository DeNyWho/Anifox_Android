package club.anifox.android.presentation.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.presentation.common.ui.theme.AnifoxAndroidTheme
import coil.compose.SubcomposeAsyncImage

@Composable
fun CardThumbnailPortrait(
    modifier: Modifier = Modifier,
    data: AnimeLight,
    thumbnailHeight: Dp = CardThumbnailPortraitDefault.Height.Default,
    textAlign: TextAlign = TextAlign.Start,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick.invoke() }
    ) {
        if (LocalInspectionMode.current) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(thumbnailHeight)
                    .clip(MaterialTheme.shapes.small)
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(thumbnailHeight)
                    .clip(MaterialTheme.shapes.small),
                model = data.image,
                contentDescription = "Content thumbnail",
                contentScale = ContentScale.Crop,
                onError = {
                    println(it.result.throwable.message)
                },
            )
        }

        Text(
            text = data.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = textAlign,
        )
    }

}

@Preview
@Composable

private fun Preview_ItemVerticalAnime_Default(
    @PreviewParameter(CardAnimeProvider::class) param: CardAnimePreviewParam,
) {
    AnifoxAndroidTheme(darkTheme = false) {
        CardThumbnailPortrait(
            modifier = param.modifier,
            data = param.data,
            thumbnailHeight = param.thumbnailHeight,
            onClick = { },
        )
    }
}
