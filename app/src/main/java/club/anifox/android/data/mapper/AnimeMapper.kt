package club.anifox.android.data.mapper

import club.anifox.android.data.local.entity.anime.image.toImage
import club.anifox.android.data.local.entity.anime.relation.AnimeAndImage
import club.anifox.android.domain.model.anime.AnimeDetails
import club.anifox.android.domain.model.anime.AnimeLight

fun AnimeAndImage.toAnimeLight(): AnimeLight = AnimeLight(
    title = anime.title,
    image = image.toImage().medium,
    url = anime.url,
)

fun AnimeAndImage.toAnimeDetails(): AnimeDetails = AnimeDetails(
    url = anime.url,
    title = anime.title,
    image = image.toImage(),
    type = anime.type,
    rating = anime.rating,
    minimalAge = anime.minimalAge,
    year = anime.year,
    status = anime.status,
    season = anime.season,
    episodes = anime.episodesCount,
    episodesAired = anime.episodesAired,
//    nextEpisode = anime.nextEpisode,
    releasedOn = anime.releasedOn,
    airedOn = anime.airedOn,
    description = anime.description,
)
