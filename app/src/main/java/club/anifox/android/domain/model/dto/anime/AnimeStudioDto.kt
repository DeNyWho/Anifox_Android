package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.common.AnimeStudio
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeStudioDto(
    @SerialName("id")
    val id: String,
    @SerialName("studio")
    val studio: String,
)

fun AnimeStudioDto.toAnimeStudio(): AnimeStudio {
    return AnimeStudio(
        id = id,
        studio = studio,
    )
}
