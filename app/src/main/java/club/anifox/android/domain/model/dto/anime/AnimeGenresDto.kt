package club.anifox.android.domain.model.dto.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenresDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)
