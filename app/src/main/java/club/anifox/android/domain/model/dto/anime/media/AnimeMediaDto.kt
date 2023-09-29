package club.anifox.android.domain.model.dto.anime.media

import club.anifox.android.domain.model.anime.media.AnimeMedia
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeMediaDto(
    @SerialName("url")
    val url: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("playerUrl")
    val playerUrl: String,
    @SerialName("name")
    val name: String,
    @SerialName("kind")
    val kind: String,
    @SerialName("hosting")
    val hosting: String
)

fun AnimeMediaDto.toAnimeMedia(): AnimeMedia {
    return AnimeMedia(
        playerUrl = playerUrl,
        name = name,
        kind = kind,
        hosting = hosting
    )
}