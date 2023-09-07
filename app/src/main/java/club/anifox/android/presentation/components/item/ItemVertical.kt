package club.anifox.android.presentation.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import club.anifox.android.R
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.presentation.common.ui.theme.Anifox_AndroidTheme
import club.anifox.android.presentation.components.indicator.CenterCircularProgressIndicator
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun ItemVertical(
    modifier: Modifier = Modifier,
    thumbnailHeight: Dp = ItemVerticalModifier.ThumbnailHeightDefault,
    data: AnimeLight = AnimeLight(),
    textAlign: TextAlign = TextAlign.Start,
    onClick: (String) -> Unit = {},
    preview: Boolean = false
) {
    Column(
        modifier = modifier
            .height(thumbnailHeight + 50.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable {
                onClick.invoke(data.url)
            }
    ) {
        if(LocalInspectionMode.current) {
            Card (
                shape = MaterialTheme.shapes.small,
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 12.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(thumbnailHeight)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.onBackground)
                )
            }
        } else {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(data.image)
                .scale(Scale.FILL)
                .build()

            Card(
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 2.dp,
                ),
                shape = MaterialTheme.shapes.small
            ) {
                if(!preview) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(thumbnailHeight)
                            .clip(MaterialTheme.shapes.small),
                        model = request,
                        contentDescription = "Item vertical image",
                        contentScale = ContentScale.Crop,
                        loading = {
                            CenterCircularProgressIndicator(
                                strokeWidth = 2.dp,
                                size = 20.dp
                            )
                        },
                        imageLoader = ImageLoader.Builder(LocalContext.current).build(),
                        onError = {
                            println(it.result.throwable.message)
                        }
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(thumbnailHeight)
                            .clip(MaterialTheme.shapes.small),
                        painter = painterResource(R.drawable.anime_test),
                        contentDescription = "Test Image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
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

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    Anifox_AndroidTheme(useDarkTheme = false) {
        ItemVertical(preview = true)
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    Anifox_AndroidTheme(useDarkTheme = true) {
        ItemVertical(preview = true)
    }
}