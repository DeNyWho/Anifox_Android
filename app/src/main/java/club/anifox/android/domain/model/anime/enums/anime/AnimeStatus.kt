package club.anifox.android.domain.model.anime.enums.anime

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import club.anifox.android.R

enum class AnimeStatus(private val displayNameResId: Int) {
    Released(R.string.status_release),
    Ongoing(R.string.status_ongoing),
    ;

    @Composable
    fun getDisplayName(): String {
        return stringResource(displayNameResId)
    }
}
