package club.anifox.android.domain.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentLight(
    @SerialName("url")
    val url: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("image")
    val image: AnimeImage = AnimeImage()
)

fun ContentLight.toContentLight(): ContentLight {
    return ContentLight(
        url = url,
        title = title,
        image = image
    )
}