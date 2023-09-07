package club.anifox.android.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeImage(
    @SerialName("large")
    val large: String = "",
    @SerialName("medium")
    val medium: String = "",
    @SerialName("cover")
    val cover: String? = null
)