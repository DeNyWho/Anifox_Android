package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.dto.anime.image.AnimeImageDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeLightDto(
    @SerialName("title")
    val title: String = "",
    @SerialName("image")
    val image: AnimeImageDto = AnimeImageDto(),
    @SerialName("url")
    val url: String = "",
    @SerialName("minimal_age")
    val minimalAge: Int = 0,
)
