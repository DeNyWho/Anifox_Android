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

@Entity(tableName = "anime")
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
    val description: String = ""
)