@file:UseSerializers(LocalDateSerializer::class, LocalDateTimeSerializer::class)

package club.anifox.android.domain.model.dto.anime.detail

import club.anifox.android.domain.model.anime.detail.AnimeDetail
import club.anifox.android.domain.model.anime.enums.AnimeSeason
import club.anifox.android.domain.model.anime.enums.AnimeStatus
import club.anifox.android.domain.model.anime.enums.AnimeType
import club.anifox.android.domain.model.dto.anime.AnimeGenresDto
import club.anifox.android.domain.model.dto.anime.AnimeImageDto
import club.anifox.android.domain.model.dto.anime.AnimeStudioDto
import club.anifox.android.domain.model.dto.anime.AnimeTranslationDto
import club.anifox.android.domain.model.dto.anime.toAnimeGenres
import club.anifox.android.domain.model.dto.anime.toAnimeStudio
import club.anifox.android.domain.model.dto.anime.toAnimeTranslation
import club.anifox.android.serialization.LocalDateSerializer
import club.anifox.android.serialization.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class AnimeDetailDto(
    @SerialName("title")
    var title: String = "",
    @SerialName("image")
    var image: AnimeImageDto = AnimeImageDto(),
    @SerialName("url")
    val url: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("rating_mpa")
    val ratingMpa: String? = null,
    @SerialName("minimal_age")
    val minimalAge: Int? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("shikimori_rating")
    val shikimoriRating: Double = 0.0,
    @SerialName("year")
    val year: Int = 0,
    @SerialName("status")
    val status: String = "",
    @SerialName("season")
    val season: String = "",
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

fun AnimeDetailDto.toAnimeDetail(): AnimeDetail {
    return AnimeDetail(
        title = title,
        largeImage = image.large,
        posterImage = image.cover ?: "",
        url = url,
        type = when (type) {
            AnimeType.Tv.name -> AnimeType.Tv
            AnimeType.Movie.name -> AnimeType.Movie
            AnimeType.Ona.name -> AnimeType.Ona
            AnimeType.Ova.name -> AnimeType.Ova
            AnimeType.Music.name -> AnimeType.Music
            AnimeType.Special.name -> AnimeType.Special
            else -> AnimeType.Tv
        },
        ratingMpa = ratingMpa,
        minimalAge = minimalAge,
        rating = rating,
        shikimoriRating = shikimoriRating,
        year = year,
        status = when (status) {
            AnimeStatus.Ongoing.name -> AnimeStatus.Ongoing
            AnimeStatus.Released.name -> AnimeStatus.Released
            else -> AnimeStatus.Ongoing
        },
        season = when (season) {
            AnimeSeason.Winter.name -> AnimeSeason.Winter
            AnimeSeason.Spring.name -> AnimeSeason.Spring
            AnimeSeason.Summer.name -> AnimeSeason.Summer
            AnimeSeason.Fall.name -> AnimeSeason.Fall
            else -> AnimeSeason.Summer
        },
        episodes = episodes,
        episodesAired = episodesAired,
        nextEpisode = nextEpisode,
        releasedOn = releasedOn,
        airedOn = airedOn,
        description = description,
        titleOther = titleOther,
        titleEnglish = titleEnglish,
        titleJapan = titleJapan,
        synonyms = synonyms,
        studio = studio.map { it.toAnimeStudio() },
        genres = genres.map { it.toAnimeGenres() },
        translations = translations.map { it.toAnimeTranslation() },
    )
}
