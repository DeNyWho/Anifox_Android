package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeLightDto(
    @SerialName("url")
    val url: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("image")
    val image: AnimeImage = AnimeImage()
)