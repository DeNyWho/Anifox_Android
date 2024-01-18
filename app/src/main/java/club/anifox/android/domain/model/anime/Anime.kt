package club.anifox.android.domain.model.anime

import club.anifox.android.domain.model.anime.genres.AnimeGenre
import club.anifox.android.domain.model.anime.image.AnimeImage
import club.anifox.android.domain.model.anime.studio.AnimeStudio
import club.anifox.android.domain.model.anime.translations.AnimeTranslations

data class Anime(
    val url: String,
    val title: String,
    val image: AnimeImage,
    val type: AnimeType,
    val rating: Double?,
    val minimalAge: Int,
    val year: Int,
    val status: AnimeStatus,
    val season: AnimeSeason,
    val episodesCount: Int,
    val episodesAired: Int,
    val genres: List<AnimeGenre>,
    val studios: List<AnimeStudio>,
    val nextEpisode: String,
    val releasedOn: String,
    val airedOn: String,
    val description: String,
    val translations: List<AnimeTranslations>,
)

fun Anime.asLight() = AnimeLight(
    title = title,
    image = image,
    url = url,
    rating = rating,
    minimalAge = minimalAge,
)

//fun Anime.asEntity() = AnimeEntity(
//    url = url,
//    title = title,
//    image = AnimeImageEntity(
//        animeUrl = url,
//        large = image.large,
//        medium = image.medium,
//    ),
//    type = type,
//    rating = rating,
//    minimalAge = minimalAge,
//    year = year,
//    status = status,
//    season = season,
//    episodesCount = episodesCount,
//    episodesAired = episodesAired,
//    genres = genres.map { AnimeGenresEntity(id = it.id, name = it.name) },
//    studios = studios.map { AnimeStudioEntity(id = it.id, name = it.name) },
//    nextEpisode = nextEpisode,
//    releasedOn = releasedOn,
//    airedOn = airedOn,
//    description = description,
//    translations = translations.map { AnimeTranslationsEntity(id = it.id, title = it.title, voice = it.voice) }
//)