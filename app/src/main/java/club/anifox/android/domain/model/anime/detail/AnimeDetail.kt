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
    val url: String = "",
    val title: String = "",
    val largeImage: String = "",
    val posterImage: String = "",
    val studio: List<AnimeStudio> = listOf(),
    val season: String = "",
    val description: String = "",
    val otherTitles: List<String> = listOf(),
    val rating: Double = 0.0,
    val year: Int = 0,
    val releasedAt: LocalDate = LocalDate.now(),
    val airedAt: LocalDate = LocalDate.now(),
    val type: String = "",
    val episodesCount: Int = 0,
    val episodesCountAired: Int = 0,
    val genres: List<AnimeGenres> = listOf(),
    val status: String = "",
    val ratingMpa: String = "",
    val minimalAge: Int = 0,
)