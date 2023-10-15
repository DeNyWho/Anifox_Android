package club.anifox.android.domain.model.anime.enums.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import club.anifox.android.R

enum class UserError(private val displayNameResId: Int) {
    TokensNotFound(R.string.tokens_not_found),
    ;

    @Composable
    fun getDisplayName(): String {
        return stringResource(displayNameResId)
    }
}
