package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeLight
import club.anifox.android.domain.model.dto.anime.image.AnimeImageDto
import club.anifox.android.domain.model.dto.anime.image.toImage
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
)

fun AnimeLightDto.toLight(): AnimeLight = AnimeLight(title, image.toImage(), url)
