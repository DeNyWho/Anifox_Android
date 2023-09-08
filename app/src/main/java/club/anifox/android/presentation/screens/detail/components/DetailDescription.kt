package club.anifox.android.presentation.screens.detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import club.anifox.android.R
import club.anifox.android.domain.model.anime.detail.AnimeDetail

@Composable
fun DetailDescription(
    data: AnimeDetail,
    isExpanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit
) {
    val descriptionGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background.copy(alpha = 0F),
            MaterialTheme.colorScheme.background.copy(alpha = 0.9F),
            MaterialTheme.colorScheme.background,
        )
    )

    val visible = data.description.length > 300

    if(data.description.isNotEmpty())  {
        Text(
            text = stringResource(R.string.description_title),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }

    if (visible) {
        AnimatedContent(
            targetState = isExpanded,
            transitionSpec = {
                expandVertically(
                    animationSpec = tween(150, 150),
                    initialHeight = { it }) togetherWith
                        shrinkVertically(
                            animationSpec = tween(150, 0),
                            targetHeight = { it }) using
                        SizeTransform(clip = true)
            }, label = ""
        ) { targetExpanded ->
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onExpandedChanged(!isExpanded) }
                    )
            ) {
                if (targetExpanded) {
                    Text(
                        text = data.description.replace("&quot;",34.toChar().toString()),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Switch",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                } else {
                    Text(
                        text = data.description.replace("&quot;",34.toChar().toString()),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                    Box(
                        modifier = Modifier
                            .zIndex(1F)
                            .fillMaxSize()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = descriptionGradient
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .zIndex(2F)
                        )
                    }
                }
            }
        }
    } else {
        Text(
            text = data.description.replace("&quot;",34.toChar().toString()),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Justify
        )
    }
}

