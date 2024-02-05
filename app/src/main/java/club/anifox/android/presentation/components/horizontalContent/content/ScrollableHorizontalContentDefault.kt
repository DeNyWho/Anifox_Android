package club.anifox.android.presentation.components.horizontalContent.content

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object ScrollableHorizontalContentDefault {
    val Default = Modifier
        .padding(horizontal = 12.dp, vertical = 8.dp)
    val Shimmer = Modifier
        .padding(start = 12.dp, end = 12.dp, bottom = 4.dp, top = 12.dp)
}
