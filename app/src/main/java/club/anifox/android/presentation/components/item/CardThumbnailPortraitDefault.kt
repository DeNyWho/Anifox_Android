package club.anifox.android.presentation.components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp

object CardThumbnailPortraitDefault {
    object Width {
        val Large = 180
        val Default = 140.dp
        val Small = 100.dp
    }

    object Height {
        val Default = 190.dp
        val Grid = 180.dp
        val Small = 170.dp
    }

    object HorizontalArrangement {
        val Default = Arrangement.spacedBy(12.dp)
        val Grid = Arrangement.spacedBy(6.dp)
    }
}
