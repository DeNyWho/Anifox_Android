@file:UseSerializers(LocalDateSerializer::class)
package club.anifox.android.domain.model.anime.detail

import club.anifox.android.domain.model.anime.AnimeGenres
import club.anifox.android.domain.model.anime.AnimeStudio
import club.anifox.android.serialization.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class AnimeDetail(
    val url: String,
    val title: String,
    val largeImage: String,
    val posterImage: String,
    val studio: List<AnimeStudio>,
    val season: String,
    val description: String,
    val otherTitles: List<String>,
    val rating: Double,
    val year: Int,
    val releasedAt: LocalDate,
    val airedAt: LocalDate,
    val type: String,
    val episodesCount: Int,
    val episodesCountAired: Int,
    val genres: List<AnimeGenres>,
    val status: String,
    val ratingMpa: String,
    val minimalAge: Int,
)