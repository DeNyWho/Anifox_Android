package club.anifox.android.domain.model.anime.enums.anime

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import club.anifox.android.R

enum class AnimeSeason(private val displayNameResId: Int) {
    Winter(R.string.season_winter),
    Spring(R.string.season_spring),
    Summer(R.string.season_summer),
    Fall(R.string.season_fall),
    ;

    @Composable
    fun getDisplayName(): String {
        return stringResource(displayNameResId)
    }
}
