@file:UseSerializers(LocalDateSerializer::class)
package club.anifox.android.domain.model.dto.anime.detail

import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.model.dto.anime.AnimeGenresDto
import club.anifox.android.domain.model.dto.anime.AnimeImageDto
import club.anifox.android.domain.model.dto.anime.AnimeStudioDto
import club.anifox.android.domain.model.dto.anime.toAnimeGenres
import club.anifox.android.domain.model.dto.anime.toAnimeStudio
import club.anifox.android.serialization.LocalDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate

@Serializable
data class AnimeDetailDto(
    @SerialName("url")
    val url: String,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: AnimeImageDto = AnimeImageDto(),
    @SerialName("studio")
    val studio: List<AnimeStudioDto>,
    @SerialName("season")
    val season: String,
    @SerialName("description")
    val description: String,
    @SerialName("otherTitles")
    val otherTitles: List<String>,
    @SerialName("rating")
    val rating: Double?,
    @SerialName("year")
    val year: Int,
    @SerialName("releasedAt")
    val releasedAt: LocalDate,
    @SerialName("airedAt")
    val airedAt: LocalDate,
    @SerialName("type")
    val type: String,
    @SerialName("episodesCount")
    val episodesCount: Int,
    @SerialName("episodesCountAired")
    val episodesCountAired: Int,
    @SerialName("genres")
    val genres: List<AnimeGenresDto>,
    @SerialName("status")
    val status: String,
    @SerialName("ratingMpa")
    val ratingMpa: String,
    @SerialName("minimalAge")
    val minimalAge: Int?,
)


fun AnimeDetailDto.toAnimeDetail(): AnimeDetail {
    return AnimeDetail(
        url = url,
        title = title,
        largeImage = image.large,
        posterImage = image.cover ?: "",
        studio = studio.map {
            it.toAnimeStudio()
        },
        season = season,
        description = description,
        otherTitles = otherTitles,
        rating = rating ?: 0.0,
        year = year,
        releasedAt = releasedAt,
        airedAt = airedAt,
        type = type,
        episodesCount = episodesCount,
        episodesCountAired = episodesCountAired,
        genres = genres.map {
            it.toAnimeGenres()
        },
        status = status,
        ratingMpa = ratingMpa,
        minimalAge = minimalAge ?: 0
    )
}