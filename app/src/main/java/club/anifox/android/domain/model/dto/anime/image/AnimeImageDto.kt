package club.anifox.android.domain.model.dto.anime.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeImageDto(
    @SerialName("large")
    val large: String = "",
    @SerialName("medium")
    val medium: String = "",
    @SerialName("cover")
    val cover: String? = null,
)
