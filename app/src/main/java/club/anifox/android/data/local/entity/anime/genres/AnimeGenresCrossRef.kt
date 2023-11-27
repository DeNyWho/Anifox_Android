package club.anifox.android.data.local.entity.anime.genres

import androidx.room.Entity

@Entity(tableName = "anime_genres_cross_ref", primaryKeys = ["animeUrl", "genreId"])
data class AnimeGenresCrossRef(
    val animeUrl: String,
    val genreId: String
)