package club.anifox.android.domain.model.user.account

import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    var session: UserSession = UserSession(),
)
