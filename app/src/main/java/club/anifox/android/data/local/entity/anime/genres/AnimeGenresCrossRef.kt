package club.anifox.android.data.local.entity.anime.genres

import androidx.room.Entity

@Entity(
    tableName = "anime_genres_cross_ref",
    primaryKeys = ["url", "genreId"]
)
data class AnimeGenresCrossRef(
    val url: String,
    val genreId: String,
)
