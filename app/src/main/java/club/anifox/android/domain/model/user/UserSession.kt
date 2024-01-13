package club.anifox.android.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val accessToken: String = "",
    val refreshToken: String = "",
)