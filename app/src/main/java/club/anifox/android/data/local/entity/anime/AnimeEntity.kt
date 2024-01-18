package club.anifox.android.data.local.entity.anime

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.AnimeType

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val url: String,
    val title: String,
    val type: AnimeType = AnimeType.Movie,
    val rating: Double? = 0.0,
    val minimalAge: Int = 0,
    val year: Int = 0,
    val status: AnimeStatus = AnimeStatus.Ongoing,
    val season: AnimeSeason = AnimeSeason.Fall,
    val episodesCount: Int = 0,
    val episodesAired: Int = 0,
    val nextEpisode: String = "",
    val releasedOn: String = "",
    val airedOn: String = "",
    val description: String = ""
)