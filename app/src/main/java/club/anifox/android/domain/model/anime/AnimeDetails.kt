package club.anifox.android.domain.model.anime

import club.anifox.android.domain.model.anime.image.AnimeImage
import java.time.LocalDate

data class AnimeDetails(
    val url: String,
    val title: String,
    val image: AnimeImage,
    val type: AnimeType,
    val rating: Double?,
    val minimalAge: Int,
    val year: Int,
    val status: AnimeStatus,
    val season: AnimeSeason,
    val episodes: Int,
    val episodesAired: Int,
//    val genres: List<AnimeGenre>,
//    val studios: List<AnimeStudio>,
//    val nextEpisode: String,
    val releasedOn: LocalDate,
    val airedOn: LocalDate,
    val description: String,
//    val translations: List<AnimeTranslations>,
)
