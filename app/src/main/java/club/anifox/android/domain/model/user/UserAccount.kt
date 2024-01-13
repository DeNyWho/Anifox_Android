package club.anifox.android.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    var session: UserSession = UserSession(),
)