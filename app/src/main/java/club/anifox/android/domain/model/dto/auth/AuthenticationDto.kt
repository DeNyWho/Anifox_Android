package club.anifox.android.domain.model.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationDto(
    @SerialName("user_identifier")
    val userIdentifier: String,
    val password: String,
)
