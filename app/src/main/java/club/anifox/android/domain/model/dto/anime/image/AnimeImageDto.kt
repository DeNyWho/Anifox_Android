package club.anifox.android.domain.model.dto.anime.image

import club.anifox.android.domain.model.anime.image.AnimeImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeImageDto(
    @SerialName("large")
    val large: String = "",
    @SerialName("medium")
    val medium: String = "",
)

fun AnimeImageDto.toImage(): AnimeImage = AnimeImage(large, medium)
