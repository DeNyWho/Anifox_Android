package club.anifox.android.domain.model.dto.anime.light

import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.model.dto.anime.AnimeImageDto
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
    @SerialName("rating_mpa")
    val ratingMpa: String = "",
    @SerialName("minimal_age")
    val minimalAge: Int = 0,
)

fun AnimeLightDto.toAnimeLight(): AnimeLight {
    return AnimeLight(
        title = title,
        image = image.medium,
        url = url,
        ratingMpa = ratingMpa,
        minimalAge = minimalAge
    )
}