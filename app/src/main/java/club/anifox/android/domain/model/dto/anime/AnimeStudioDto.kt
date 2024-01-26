package club.anifox.android.domain.model.dto.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeStudioDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val studio: String,
)
