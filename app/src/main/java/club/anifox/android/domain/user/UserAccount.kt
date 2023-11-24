package club.anifox.android.domain.user

import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    var session: UserSession = UserSession(),
)