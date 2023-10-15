package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeGenres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenresDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)

fun AnimeGenresDto.toAnimeGenres(): AnimeGenres {
    return AnimeGenres(
        id = id,
        name = name,
    )
}
