package club.anifox.android.domain.model.user.account

data class TokenPair(
    val accessToken: String,
    val refreshToken: String,
)
