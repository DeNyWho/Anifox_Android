@file:UseSerializers(
    LocalDateSerializer::class,
    LocalDateTimeSerializer::class,
)

package club.anifox.android.domain.model.anime.detail

import club.anifox.android.domain.model.anime.AnimeGenres
import club.anifox.android.domain.model.anime.AnimeStudio
import club.anifox.android.domain.model.anime.AnimeTranslation
import club.anifox.android.domain.model.anime.enums.anime.AnimeSeason
import club.anifox.android.domain.model.anime.enums.anime.AnimeStatus
import club.anifox.android.domain.model.anime.enums.anime.AnimeType
import club.anifox.android.serialization.LocalDateSerializer
import club.anifox.android.serialization.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class AnimeDetail(
    val title: String = "",
    val largeImage: String = "",
    val posterImage: String = "",
    val url: String = "",
    val type: AnimeType = AnimeType.Tv,
    val ratingMpa: String? = null,
    val minimalAge: Int? = null,
    val rating: Double? = null,
    val shikimoriRating: Double = 0.0,
    val year: Int = 0,
    val status: AnimeStatus = AnimeStatus.Ongoing,
    val season: AnimeSeason = AnimeSeason.Summer,
    val episodes: Int = 0,
    val episodesAired: Int = 0,
    val nextEpisode: LocalDateTime? = null,
    val releasedOn: LocalDate = LocalDate.now(),
    val airedOn: LocalDate = LocalDate.now(),
    val description: String = "",
    val titleOther: List<String>? = null,
    val titleEnglish: List<String>? = null,
    val titleJapan: List<String>? = null,
    val synonyms: List<String>? = null,
    val studio: List<AnimeStudio> = listOf(),
    val genres: List<AnimeGenres> = listOf(),
    val translations: List<AnimeTranslation> = listOf(),
)
