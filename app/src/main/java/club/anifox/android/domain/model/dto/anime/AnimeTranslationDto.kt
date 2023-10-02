package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeTranslation
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTranslationDto(
    val id: Int = 0,
    val title: String = "",
    val voice: String = "",
)

fun AnimeTranslationDto.toAnimeTranslation(): AnimeTranslation {
    return AnimeTranslation(
        id = id,
        title = title,
        voice = voice,
    )
}
