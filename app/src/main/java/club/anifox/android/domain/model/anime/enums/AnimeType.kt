package club.anifox.android.domain.model.anime.enums

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import club.anifox.android.R

enum class AnimeType(private val displayNameResId: Int) {
    Movie(R.string.type_movie),
    Ona(R.string.type_ona),
    Ova(R.string.type_ova),
    Music(R.string.type_music),
    Special(R.string.type_special),
    Tv(R.string.type_tv);

    @Composable
    fun getDisplayName(): String {
        return stringResource(displayNameResId)
    }
}