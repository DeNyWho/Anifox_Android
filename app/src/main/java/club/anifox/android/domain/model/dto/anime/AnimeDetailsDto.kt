@file:UseSerializers(LocalDateSerializer::class, LocalDateTimeSerializer::class)

package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeDetails
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.AnimeType
import club.anifox.android.domain.model.dto.anime.image.AnimeImageDto
import club.anifox.android.domain.model.dto.anime.image.toImage
import club.anifox.android.serialization.LocalDateSerializer
import club.anifox.android.serialization.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class AnimeDetailsDto(
    @SerialName("title")
    var title: String = "",
    @SerialName("image")
    var image: AnimeImageDto = AnimeImageDto(),
    @SerialName("url")
    val url: String = "",
    @SerialName("type")
    val type: AnimeType = AnimeType.Tv,
    @SerialName("rating_mpa")
    val ratingMpa: String? = null,
    @SerialName("minimal_age")
    val minimalAge: Int = 0,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("shikimori_rating")
    val shikimoriRating: Double = 0.0,
    @SerialName("year")
    val year: Int = 0,
    @SerialName("status")
    val status: AnimeStatus = AnimeStatus.Ongoing,
    @SerialName("season")
    val season: AnimeSeason = AnimeSeason.Fall,
    @SerialName("episodes")
    val episodes: Int = 0,
    @SerialName("episodes_aired")
    val episodesAired: Int = 0,
    @SerialName("next_episode_on")
    val nextEpisode: LocalDateTime? = null,
    @SerialName("released_on")
    val releasedOn: LocalDate = LocalDate.now(),
    @SerialName("aired_on")
    val airedOn: LocalDate = LocalDate.now(),
    @SerialName("description")
    val description: String = "",
    @SerialName("other_title")
    val titleOther: List<String>? = null,
    @SerialName("english")
    val titleEnglish: List<String>? = null,
    @SerialName("japanese")
    val titleJapan: List<String>? = null,
    val synonyms: List<String> = listOf(),
    val studio: List<AnimeStudioDto> = listOf(),
    val genres: List<AnimeGenresDto> = listOf(),
    val translations: List<AnimeTranslationDto> = listOf(),
)

fun AnimeDetailsDto.toDetails(): AnimeDetails = AnimeDetails(
    url = url,
    title = title,
    image = image.toImage(),
    type = type,
    rating = rating,
    minimalAge = minimalAge,
    ratingMpa = ratingMpa,
    year = year,
    status = status,
    season = season,
    episodes = episodes,
    episodesAired = episodesAired,
    nextEpisode = nextEpisode,
    releasedOn = releasedOn,
    airedOn = airedOn,
    description = description,
)
