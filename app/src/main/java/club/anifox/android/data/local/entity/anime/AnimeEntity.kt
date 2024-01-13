package club.anifox.android.data.local.entity.anime

import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresCrossRef
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresEntity
import club.anifox.android.domain.model.anime.FilmSeason
import club.anifox.android.domain.model.anime.FilmStatus
import club.anifox.android.domain.model.anime.FilmType

@Entity(tableName = "film")
data class AnimeEntity(
    @PrimaryKey val url: String,
    val title: String,
    val type: FilmType = FilmType.Movie,
    val rating: Double? = 0.0,
    val minimalAge: Int = 0,
    val year: Int = 0,
    val status: FilmStatus = FilmStatus.Ongoing,
    val season: FilmSeason = FilmSeason.Fall,
    val episodesCount: Int = 0,
    val episodesAired: Int = 0,
    val nextEpisode: String = "",
    val releasedOn: String = "",
    val airedOn: String = "",
    val description: String = "",
    @Relation(
        parentColumn = "url",
        entityColumn = "genreId",
        associateBy = Junction(AnimeGenresCrossRef::class)
    )
    val genres: List<AnimeGenresEntity>
)

//fun AnimeEntity.asAnime() = Anime(
//    url = url,
//    title = title,
//    image = image.asImage(),
//    type = type,
//    rating = rating,
//    minimalAge = minimalAge,
//    year = year,
//    status = status,
//    season = season,
//    episodesCount = episodesCount,
//    episodesAired = episodesAired,
//    genres = genres.map { it.asGenre() },
//    studios = studios.map { it.asStudio() },
//    nextEpisode = nextEpisode,
//    releasedOn = releasedOn,
//    airedOn = airedOn,
//    description = description,
//    translations = translations.map { it.asTranslation() }
//)