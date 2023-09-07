package club.anifox.android.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeLight(
    @SerialName("url")
    val url: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("image")
    val image: String = ""
)

fun AnimeLight.toAnimeLight(): AnimeLight {
    return AnimeLight(
        url = url,
        title = title,
        image = image
    )
}