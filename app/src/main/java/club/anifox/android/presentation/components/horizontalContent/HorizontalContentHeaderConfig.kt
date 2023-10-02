package club.anifox.android.presentation.components.horizontalContent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object HorizontalContentHeaderConfig {
    val Default = Modifier
        .fillMaxWidth()
    val NullableStart = Modifier
        .fillMaxWidth()
        .padding(start = 0.dp, end = 12.dp, bottom = 4.dp)
    val Home = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 0.dp, bottom = 4.dp)
    val fillWidth = Modifier.fillMaxWidth()
    val Detail = Modifier
        .fillMaxWidth()
        .padding(start = 0.dp, end = 0.dp, bottom = 4.dp)
}
